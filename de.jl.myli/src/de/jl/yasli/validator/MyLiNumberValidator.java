package de.jl.yasli.validator;

public class MyLiNumberValidator implements IMyLiValidator {
	
	private static MyLiNumberValidator validator = null;
	
	public static MyLiNumberValidator getValidator() {
		if (validator == null) {
			validator = new MyLiNumberValidator();
		}
		return validator;
	}
	

	public Boolean isValid(String test, Integer type) {
		if(type==ENGLISH) {
			return validateEnglishFormat(test);
		} else if(type==GERMAN) {
			return validateGermanFormat(test);
		} else if(type==UKN) {
			return validateNumber(test);
		} else 
			return false;
	}
	
	/* Englisches Format
	 * Dezimalstellen		: (([0-9]{1,3}[\\,]{1})*([0-9]{3})|([0-9]{1,3}))
	 * Entweder				: ([0-9]{1,3}[\\,]{1})*([0-9]{3}):
	 * [0-9]{1,3}			: 1 - 3 stellige Ziffernfolge
	 * [\\,]{1}				: Gefolgt von genau einem ','
	 * ([0-9]{1,3}[\\,]{1})*: Das kann sich beliebig oft wiederholen
	 * [0-9]{3}				: Davor genau eine 3 stellige Zahl
	 * 
	 * ODER					: |([0-9]{1,3}))
	 * |					: ODER
	 * ([0-9]{1,3})			: Eine 1 - 3-stellige Zahl
	 *  
	 * Nachkommastellen: ([\\.]{1}[0-9]+){0,1} 
	 * [\\.]{1} : genau ein '.'  
	 * [0-9]+	: gefolgt von beliebig vielen Ziffern. 
	 * {0,1}	: Insgesamt maximal 1 Wiederholung
	 */
	public Boolean validateEnglishFormat(String test) {
	    return test.matches("(([0-9]{1,3}[\\,]{1})*([0-9]{3})|([0-9]{1,3}))([\\.]{1}[0-9]+){0,1}");
	}
	
	/* Deutsches Format
	 * Dezimalstellen		: (([0-9]{1,3}[\\.]{1})*([0-9]{3})|([0-9]{1,3}))
	 * Entweder				: ([0-9]{1,3}[\\.]{1})*([0-9]{3}):
	 * [0-9]{1,3}			: 1 - 3 stellige Ziffernfolge
	 * [\\.]{1}				: Gefolgt von genau einem '.'
	 * ([0-9]{1,3}[\\.]{1})*: Das kann sich beliebig oft wiederholen
	 * [0-9]{3}				: Davor genau eine 3 stellige Zahl
	 * 
	 * ODER					: |([0-9]{1,3}))
	 * |					: ODER
	 * ([0-9]{1,3})			: Eine 1 - 3-stellige Zahl
	 *  
	 * Nachkommastellen: ([\\,]{1}[0-9]+){0,1} 
	 * [\\,]{1} : genau ein ','  
	 * [0-9]+	: gefolgt von beliebig vielen Ziffern. 
	 * {0,1}	: Insgesamt maximal 1 Wiederholung
	 */
	public Boolean validateGermanFormat(String test) {
	    return test.matches("(([0-9]{1,3}[\\.]{1})*([0-9]{3})|([0-9]{1,3}))([\\,]{1}[0-9]+){0,1}");
	}

	public Boolean validateNumber(String test) {
		if (this.validateEnglishFormat(test))
			return true;
		else if (this.validateGermanFormat(test))
			return true;
		else 
			return test.matches("\\d+([\\.]{1}\\d+){0,1}"); 
	}
	
	public Integer getNumberFormat(String number) {
		if(validateEnglishFormat(number)) {
			return IMyLiValidator.ENGLISH;
		}
		if(validateGermanFormat(number)) {
			return IMyLiValidator.GERMAN;
		}
		if(validateNumber(number)) {
			return IMyLiValidator.UKN;
		}
		return IMyLiValidator.NOTSET;
	}
	
	public String resultToString(String test, Integer type) {
		if (isValid(test, type)) {
			return "G�ltige Nummer: [" + test + "]"; 
		} else {
			return "Keine g�ltige Nummer: [" + test + "]";
		}
	}
}
