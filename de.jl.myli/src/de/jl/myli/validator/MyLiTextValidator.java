package de.jl.myli.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyLiTextValidator implements IMyLiValidator {

	public static final Integer NOTSET = -1;
	public static final Integer UKN = 0;
	public static final Integer TEXTCONSTANT = 1;
	public static final Integer VARIABLENAME = 2;
	
	private static MyLiTextValidator validator = null;
	
	public static MyLiTextValidator getValidator() {
		if (validator == null) {
			validator = new MyLiTextValidator();
		}
		return validator;
	}
	
	@Override
	public Boolean isValid(String test, Integer type) {
		if(type==TEXTCONSTANT) {
			return validateTextConstantFormat(test);
		} else if(type==VARIABLENAME) {
			return validateVariableFormat(test);
		} else if(type==UKN) {
			return validateText(test);
		} else 
			return false;
	}
	
	/* 
	 * Keine besonderen Anforderungen.
	 */
	private Boolean validateTextConstantFormat(String test) {
	    return true;
	}
	
	/* Erlaubtes Format Variablen: ([a-zA-Z]{1}[\\.]{0,1}([a-zA-Z0-9]+[\\.]{0,1})*)
	 * ----------------------------------------------------------------------------
	 * ([a-zA-Z]{1}					: Variable beginnt mit einem Buchstaben
	 * [\\.]{0,1}					: Es folgt maximal ein '.'
	 * 
	 * ([a-zA-Z0-9]+[\\.]{0,1})*	: Folgende Zeichenfolge kommt beliebig oft vor.
	 * ----------------------------------------------------------------------------
	 * [a-zA-Z0-9]+					: Es folgt mindestens ein alphanummerisches Zeichen
	 * [\\.]{0,1}					: Es folgt maximal ein '.'
	 * 
	 */
	private Boolean validateVariableFormat(String test) {
		return test.matches("([a-zA-Z]{1}[\\.]{0,1}([a-zA-Z0-9]+[\\.]{0,1})*)");
	}

	private Boolean validateText(String test) {
		if (this.validateTextConstantFormat(test))
			return true;
		else if (this.validateVariableFormat(test))
			return true;
		else 
			return test.matches("\\w");
	}

	public String resultTextConstantsToString(String test) {
		if (validateTextConstantFormat(test)) {
			return "Gültige Konstante: [" + test + "]"; 
		} else {
			return "Ungültige Konstante: [" + test + "]";
		}
	}
	public String resultVariableToString(String test) {
		if (validateVariableFormat(test)) {
			return "Gültige Variable: [" + test + "]"; 
		} else {
			return "Ungültige Variable: [" + test + "]";
		}
	}
	public String resultToString(String test, Integer type) {
		if (type == TEXTCONSTANT )
			return resultTextConstantsToString(test);
		else if(type == VARIABLENAME)
			return resultVariableToString(test);
		else 
			return "-- no result --";
	}

}
