package de.jl.myli;

import de.jl.myli.items.MyLiItem;

public class MyValidator {

	public static final Character SINGLEQUOTES = 0x0027; //"\u0027";
	public static final Character QUOTES = 0x0022;//"u0022";
	
	public static Integer getVarType(String var) {
		
		switch (var.charAt(0)) {
		case 0x0022: //MyValidator.QUOTES: 
		case 0x0027: //MyValidator.SINGLEQUOTES:
			return MyLiItem.OPERATOR;
		default:
			return MyLiItem.NUMBER;		
		}
		
		
		
	}

}
