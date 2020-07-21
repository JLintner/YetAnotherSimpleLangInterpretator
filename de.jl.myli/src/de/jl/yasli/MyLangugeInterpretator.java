package de.jl.yasli;

import java.io.File;
import java.util.List;

import de.jl.yasli.items.MyLiItem;
import de.jl.yasli.items.MyLiVar;
import de.jl.yasli.utils.MyLiTaskReader;
import de.jl.yasli.utils.MyLiUtils;
import de.jl.yasli.utils.MyLiValidatorException;
import de.jl.yasli.utils.MyLiVarsReader;
import de.jl.yasli.validator.IMyLiValidator;
import de.jl.yasli.validator.MyLiNumberValidator;
import de.jl.yasli.validator.MyLiTextValidator;

public class MyLangugeInterpretator {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String taskfilename = "";
		String varfilename = "";
		

		
		if(args.length == 2) {
			if(args[0].startsWith("-t")) {
				taskfilename = args[0].substring(2);
			}
			if(args[0].startsWith("-p")) {
				varfilename = args[0].substring(2);
			}
			if(args[1].startsWith("-t")) {
				taskfilename = args[1].substring(2);
			}
			if(args[1].startsWith("-p")) {
				varfilename = args[1].substring(2);
			}
		} else {
			String s = "";

			s = s.concat("There are missing parameter. Please add.").concat(System.lineSeparator());
			s = s.concat(System.lineSeparator());
			s = s.concat(printHelp());
			System.out.println(s);
			System.exit(1);
		}
		doTasks(taskfilename, varfilename);
		
		System.exit(0);
	}

	private static String printHelp() {
		String s = "";
		s = s.concat("Help conent:").concat(System.lineSeparator());
		s = s.concat("-------").concat(System.lineSeparator());
		s = s.concat("-t: \tPath to task file; e.g. -t/home/jl/res/task.myli").concat(System.lineSeparator());
		s = s.concat("-p: \tPath to variables file; e.g. -t/home/jl/res/var.myli").concat(System.lineSeparator());
		return s;
	}
	
	private static void doTasks(String taskFileName, String varsFileName) {
		List<List<MyLiItem>> tasks = null;
		MyLiTaskReader tfr = null;
		List<MyLiVar> vars = null;
		MyLiVarsReader vfr = null;
		File taskFile = new File(taskFileName);
		if(taskFile!= null) {
			tfr = new MyLiTaskReader(taskFile);
			tasks = tfr.readFile();
		}
		File varsFile = new File(varsFileName);
		if(varsFile!= null) {
			vfr = new MyLiVarsReader(varsFile);
			try {
				vars = vfr.readFile();
			} catch (MyLiValidatorException e) {
				String msg = "Error reading variables file from:  " + varsFileName;
				msg = msg.concat(System.lineSeparator());
				msg = msg.concat(e.getMessage()).concat(System.lineSeparator());
				System.out.println(msg);
				System.exit(1);
			}
		}
		MyLiTask task = new MyLiTask(tasks, vars);
		MyLiValidator valid = new MyLiValidator(vars);
		for (Integer i=0; i<vars.size(); i++) {
			try {
				valid.validate(vars.get(i.intValue()));
			} catch (MyLiValidatorException e) {
				String msg = "Error during validation:  " + e.getMessage();
				msg = msg.concat(System.lineSeparator());
				msg = msg.concat(e.getMessage()).concat(System.lineSeparator());
				System.out.println(msg);
				System.exit(1);
			}
		}
		for (Integer i=0; i<tasks.size(); i++) {
			try {
				valid.validate(tasks.get(i.intValue()));
			} catch (MyLiValidatorException e) {
				String msg = "Error during validation:  " + e.getMessage();
				msg = msg.concat(System.lineSeparator());
				msg = msg.concat(e.getMessage()).concat(System.lineSeparator());
				System.out.println(msg);
				System.exit(1);
			}
		}
		
		MyLiTaskRunner tr = new MyLiTaskRunner(task);

		try {
			System.out.println(tr.runTasks());
		} catch (MyLiValidatorException e) {
			String msg = "Error during validation:  " + e.getMessage();
			msg = msg.concat(System.lineSeparator());
			msg = msg.concat(e.getMessage()).concat(System.lineSeparator());
			System.out.println(msg);
			System.exit(1);
		}

	}
	
}
