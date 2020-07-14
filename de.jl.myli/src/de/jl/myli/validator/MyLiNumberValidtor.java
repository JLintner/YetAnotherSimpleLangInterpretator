package de.jl.myli.validator;

public class MyLiNumberValidtor implements IMyLiValidator {

	public static final Integer NOTSET = -1;
	public static final Integer UKN = 0;
	public static final Integer GERMAN = 1;
	public static final Integer ENGLISH = 2;
	
	private static MyLiNumberValidtor validator = null;
	
	public static MyLiNumberValidtor getValidator() {
		if (validator == null) {
			validator = new MyLiNumberValidtor();
		}
		return validator;
	}
	
	@Override
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
	private Boolean validateEnglishFormat(String test) {
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
	private Boolean validateGermanFormat(String test) {
	    return test.matches("(([0-9]{1,3}[\\.]{1})*([0-9]{3})|([0-9]{1,3}))([\\,]{1}[0-9]+){0,1}");
	}

	private Boolean validateNumber(String test) {
		if (this.validateEnglishFormat(test))
			return true;
		else if (this.validateGermanFormat(test))
			return true;
		else 
			return test.matches("\\d+([\\,]{1}\\d+){0,1}");
	}
	
	public String resultToString(String test, Integer type) {
		if (isValid(test, type)) {
			return "Gültige Nummer: [" + test + "]"; 
		} else {
			return "Keine gültige Nummer: [" + test + "]";
		}
	}
}
