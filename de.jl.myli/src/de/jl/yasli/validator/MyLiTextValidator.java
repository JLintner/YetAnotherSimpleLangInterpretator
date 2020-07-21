package de.jl.yasli.validator;

import de.jl.yasli.utils.UniCodeChars;

public class MyLiTextValidator implements IMyLiValidator {

	private static MyLiTextValidator validator = null;
	
	public static MyLiTextValidator getValidator() {
		if (validator == null) {
			validator = new MyLiTextValidator();
		}
		return validator;
	}
	
	public Boolean isValid(String test, Integer validationType) {
		if(validationType==CONSTANT) {
			return validateConstantFormat(test);
		} else if(validationType==TEXT) {
			return validateTextFormat(test);
		} else if(validationType==VARIABLENAME) {
			return validateVariableFormat(test);
		} else if(validationType==UKN) {
			return validateText(test);
		} else 
			return false;
	}
	
	/* 
	 * Keine besonderen Anforderungen.
	 */
	protected Boolean validateConstantFormat(String test) {
	    return isConstant(test);
	}
	protected Boolean validateTextFormat(String test) {
	    return isText(test);
	}

	protected Boolean isConstant(String value) {
		if (value.indexOf(UniCodeChars.APOSTROPHE) == 0) {
			if (value.lastIndexOf(UniCodeChars.APOSTROPHE) == value.length()-1) {
				return true;
			}
		}
		return false || isText(value);
	}
	protected Boolean isText(String value) {
		if (value.indexOf(UniCodeChars.QUOTATIONMARK) == 0) {
			if (value.lastIndexOf(UniCodeChars.QUOTATIONMARK) == value.length()-1) {
				return true;
			}
		}
		return false;
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
	protected Boolean validateVariableFormat(String test) {
		return test.matches("([a-zA-Z]{1}[\\.]{0,1}([a-zA-Z0-9]+[\\.]{0,1})*)");
	}

	protected Boolean validateText(String test) {
		if (this.validateTextFormat(test))
			return true;
		else if (this.validateVariableFormat(test))
			return true;
		else 
			return test.matches("\\w");
	}

	public String resultConstantsToString(String test) {
		if (validateConstantFormat(test)) {
			return "G�ltige Konstante: [" + test + "]"; 
		} else {
			return "Ung�ltige Konstante: [" + test + "]";
		}
	}
	public String resultTextToString(String test) {
		if (validateTextFormat(test)) {
			return "G�ltige Konstante: [" + test + "]"; 
		} else {
			return "Ung�ltige Konstante: [" + test + "]";
		}
	}
	public String resultVariableToString(String test) {
		if (validateVariableFormat(test)) {
			return "G�ltige Variable: [" + test + "]"; 
		} else {
			return "Ung�ltige Variable: [" + test + "]";
		}
	}
	public String resultToString(String test, Integer type) {
		if (type == CONSTANT )
			return resultConstantsToString(test);
		else if (type == TEXT)
			return resultTextToString(test);
		else if(type == VARIABLENAME)
			return resultVariableToString(test);
		else 
			return "-- no result --";
	}

}
