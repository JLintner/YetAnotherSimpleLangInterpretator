package de.jl.myli.constants;

public class UniCodeChars {

	public static final Integer NOTSET = -1;
	
	/*
	 * Brackets
	 */
	public static final Integer LEFTPARENTHIS = 0x0028; // '('
	public static final Integer RIGHTPARENTHIS = 0x0029; // ')'
	public static final Integer LEFTSQUAREBRACKET = 0x005B; //  '['
	public static final Integer RIGHTSQUAREBRACKET = 0x005C; //  ']'
	public static final Integer LEFTCURLYBRACKET = 0x007B; //  '{'
	public static final Integer RIGHTCURLYBRACKET = 0x007D; // '}'
	
	/*
	 * QUOTATIONMARK, APOSTROPHE
	 */
	public static final Integer QUOTATIONMARK = 0x0022; // ' " '
	public static final Integer APOSTROPHE = 0x0027; // ' ' '
	
	/*
	 * Matehmatical Operators
	 */
	public static final Integer ASTERISK = 0x002A; // '*'
	public static final Integer PLUS  = 0x002B; // '+'
	public static final Integer MINUS  = 0x002D; // '-'
	public static final Integer HYPHEN  = 0x002D; // '-'
	public static final Integer HYPHEN_MINUS  = 0x002D; // '-'
	public static final Integer SOLIDUS = 0x002F; // '/'

	/*
	 * Others
	 */
	public static final Integer BLANK = 0x0020; // ' '
	public static final Integer SPACE = 0x0020; // ' '
	public static final Integer COMMA = 0x002C; // ,'
	public static final Integer FULLSTOP = 0x002E; // '.'
	

	public static Boolean isBlank(Character c) {
		if(isEqual(BLANK, c))
			return true;
		else
			return false;
	}
	public static Boolean isBracket(Character c) {
		return isClosingBracket(c) || isOpeningBracket(c);
	}
	public static Boolean isClosingBracket(Character c) {
		if(isEqual(RIGHTPARENTHIS, c))
			return true;
		else if(isEqual(RIGHTSQUAREBRACKET, c))
			return true;
		else if(isEqual(RIGHTCURLYBRACKET, c))
			return true;
		else
			return false;
	}
	public static Boolean isEqual(Integer uniCodeChar,  Character c) {
		return uniCodeChar == Character.hashCode(c);
	}
	public static Boolean isPunctiation(Character c) {
		if(isEqual(COMMA, c) || isEqual(FULLSTOP, c)) 
			return true;
		else
			return false;
	}
	public static Boolean isMathematicalOperator(Character c) {
		if(isEqual(ASTERISK, c))
			return true;
		else if(isEqual(PLUS, c))
			return true;
		else if(isEqual(MINUS, c))
			return true;
		else if(isEqual(SOLIDUS, c))
			return true;
		else
			return false;
	}
	public static Boolean isOpeningBracket(Character c) {
		if(isEqual(LEFTPARENTHIS, c))
			return true;
		else if(isEqual(LEFTSQUAREBRACKET, c))
			return true;
		else if(isEqual(LEFTCURLYBRACKET, c))
			return true;
		else
			return false;
	}
	public static Boolean isOperator(Character c) {
		return isBracket(c) || isMathematicalOperator(c) || 
				isEqual(APOSTROPHE , c) || isEqual(QUOTATIONMARK, c);
	}
}
