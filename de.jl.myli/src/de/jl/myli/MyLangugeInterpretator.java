package de.jl.myli;

import java.io.File;
import java.util.List;

import de.jl.myli.items.MyLiItem;
import de.jl.myli.items.MyLiVar;
import de.jl.myli.utils.MyLiTaskReader;
import de.jl.myli.utils.MyLiVarsReader;
import de.jl.myli.validator.MyLiNumberValidtor;
import de.jl.myli.validator.MyLiTextValidator;

public class MyLangugeInterpretator {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<List<MyLiItem>> tasks = null;
		MyLiTaskReader tfr = null;
		List<MyLiVar> vars = null;
		MyLiVarsReader vfr = null;
		File taskFile = new File("res/codeAll.myli");
		if(taskFile!= null) {
			tfr = new MyLiTaskReader(taskFile);
			tasks = tfr.readFile();
		}
		File varsFile = new File("res/vars.myli");
		if(varsFile!= null) {
			vfr = new MyLiVarsReader(varsFile);
			vars = vfr.readFile();
		}
		
		
		MyLiTask task = null;
		//task = new MyLiTask(new File("res/vars.myli"),null);
		//task = new MyLiTask(null, new File("res/code2.myli"));
		task = new MyLiTask(tasks, vars);
		if (task != null) {
			System.out.println("Content VarsFile: " + System.lineSeparator() + vfr.fileToString());
			System.out.println("Content TaskFile: " + System.lineSeparator() + tfr.fileToString());
			System.out.println("Result Import: " + System.lineSeparator() + task.toString());
		}

		task.runTasks();
		
		//testVariableValidation();
		//testConstantsValidation();
		//testNumberValidation();
		
		System.exit(0);
	}
	
	private static void testVariableValidation() {
		String s = "";
		s = "a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "a.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "A.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "aA";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "Aa";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "a1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "A1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "aA1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "a1A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "Aa1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "A1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "aA1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "a1A.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "Aa1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "A1.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "A.1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "A.1.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		System.out.println();
		System.out.println("ab hier ungültig:");
		System.out.println("-----------------");
		s = ".a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = ".A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = ".1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "1A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "A..1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "1..A.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "..a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		s = "1a..";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.VARIABLENAME));
		
	}
	
	private static void testConstantsValidation() {
		String s = "";
		s = "a";
		s = "a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "a.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "A.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "aA";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "Aa";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "a1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "A1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "aA1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "a1A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "Aa1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "A1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "aA1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "a1A.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "Aa1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "A1.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "A.1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "A.1.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = ".a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = ".A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = ".1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "1A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "A..1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "1..A.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "..a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		s = "1a..";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, MyLiTextValidator.TEXTCONSTANT));
		
	}
	
	private static void testNumberValidation() {
		String s = "";
		s = "1";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = "11,1";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = "111";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = "1.111";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = "1.111,11";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = "1.111.111";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = "0,111";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		System.out.println();
		System.out.println("ab hier ungültig:");
		System.out.println("-----------------");
		s = "111111";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = "1.111.11";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = "1,111,11";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = "1,111.11";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = ",111";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = ".111";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = "1..111,11";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
		s = "1.111,,11";
		System.out.println(MyLiNumberValidtor.getValidator().resultToString(s, MyLiNumberValidtor.UKN));
	}

}
