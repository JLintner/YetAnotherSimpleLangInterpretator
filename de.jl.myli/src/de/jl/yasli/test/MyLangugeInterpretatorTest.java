package de.jl.yasli.test;

import de.jl.yasli.MyLangugeInterpretator;
import de.jl.yasli.utils.MyLiUtils;
import de.jl.yasli.utils.MyLiValidatorException;
import de.jl.yasli.validator.IMyLiValidator;
import de.jl.yasli.validator.MyLiNumberValidator;
import de.jl.yasli.validator.MyLiTextValidator;

public class MyLangugeInterpretatorTest {

	public MyLangugeInterpretatorTest() {
		
	}

	public static void main(String args[]) {
		
		String[] empty = new String[1];
		String[] files = new String[2];
		files[0] = "-tsrc/resources/codeAll.myli";
		files[1]= "-psrc/resources/vars.myli";
				
		MyLangugeInterpretator.main(empty);
		

		

		//testStringToDouble();
		
//		testNumberConvert();
		//testVariableValidation();
		//testConstantsValidation();
		//testNumberValidation();
	}
	
	private static void testNumberConvert() {
		Double s = 0d;
		s = 1d;
		System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.GERMAN));
		s = 11d;
		System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.GERMAN));
		s = 111d;
		System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.GERMAN));
		s = 1111d;
		System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.GERMAN));
		s = 11111d;
		System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.GERMAN));
		s = 111111d;
		System.out.println(s + "\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.GERMAN));
		s = 1111111d;
		System.out.println(s + "\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.GERMAN));
		s = 1111.11d;
		System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.GERMAN));
		s = 1111111.11d;
		System.out.println(s + "\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.GERMAN));
			

			System.out.println();
			System.out.println("ab hier ENGLISH:");
			System.out.println("-----------------");
			s = 1d;
			System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.ENGLISH));
			s = 11d;
			System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.ENGLISH));
			s = 111d;
			System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.ENGLISH));
			s = 1111d;
			System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.ENGLISH));
			s = 11111d;
			System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.ENGLISH));
			s = 111111d;
			System.out.println(s + "\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.ENGLISH));
			s = 1111111d;
			System.out.println(s + "\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.ENGLISH));
			s = 1111.11d;
			System.out.println(s + "\t\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.ENGLISH));
			s = 1111111.11d;
			System.out.println(s + "\t: " + MyLiUtils.converNumberformat(s, IMyLiValidator.ENGLISH));
	}
	
	private static void testStringToDouble() throws MyLiValidatorException {
		String s = "";
		try {
			s = "1";
			System.out.println(s + "\t\t: " + MyLiUtils.convertToDouble(s));
			s = "1,1";
			System.out.println(s + "\t\t: " + MyLiUtils.convertToDouble(s));
			s = "1.111";
			System.out.println(s + "\t\t: " + MyLiUtils.convertToDouble(s));
			s = "1.111,1";
			System.out.println(s + "\t\t: " + MyLiUtils.convertToDouble(s));
			s = "1.111,10";
			System.out.println(s + "\t: " + (MyLiUtils.convertToDouble(s)));
			s = "1.111,0";
			System.out.println(s + "\t\t: " + (MyLiUtils.convertToDouble(s)));
			s = "1,111";
			System.out.println(s + "\t\t: " + MyLiUtils.convertToDouble(s));
			s = "1,111.1";
			System.out.println(s + "\t\t: " + MyLiUtils.convertToDouble(s));
			s = "1,111,111.1";
			System.out.println(s + "\t: " + MyLiUtils.convertToDouble(s));
			s = "1.111.111,1";
			System.out.println(s + "\t: " + MyLiUtils.convertToDouble(s));
		} catch (MyLiValidatorException e) {
			System.out.println("Fehler beim Encoding von: " + s);
		}
		
	}

	private static void testVariableValidation() {
		String s = "";
		s = "a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "a.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "A.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "aA";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "Aa";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "a1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "A1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "aA1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "a1A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "Aa1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "A1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "aA1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "a1A.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "Aa1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "A1.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "A.1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "A.1.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		System.out.println();
		System.out.println("ab hier ung�ltig:");
		System.out.println("-----------------");
		s = ".a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = ".A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = ".1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "1A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "A..1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "1..A.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "..a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		s = "1a..";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.VARIABLENAME));
		
	}
	
	private static void testConstantsValidation() {
		String s = "";
		s = "a";
		s = "a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "a.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "A.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "aA";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "Aa";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "a1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "A1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "aA1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "a1A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "Aa1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "A1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "aA1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "a1A.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "Aa1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "A1.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "A.1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "A.1.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = ".a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = ".A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = ".1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "1.";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "1a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "1A";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "A..1";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "1..A.a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "..a";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		s = "1a..";
		System.out.println(MyLiTextValidator.getValidator().resultToString(s, IMyLiValidator.TEXT));
		
	}
	
	private static void testNumberValidation() {
		Integer numberFormat = IMyLiValidator.GERMAN;
		String s = "";
		s = "1";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = "11,1";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = "111";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = "1.111";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = "1.111,11";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = "1.111.111";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = "0,111";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		System.out.println();
		System.out.println("ab hier ung�ltig:");
		System.out.println("-----------------");
		s = "111111";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = "1.111.11";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = "1,111,11";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = "1,111.11";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = ",111";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = ".111";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = "1..111,11";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
		s = "1.111,,11";
		System.out.println(MyLiNumberValidator.getValidator().resultToString(s, numberFormat));
	}

}
