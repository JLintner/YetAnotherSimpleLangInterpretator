package de.jl.yasli;

import java.util.List;

import de.jl.yasli.items.MyLiItem;
import de.jl.yasli.items.MyLiVar;
import de.jl.yasli.utils.MyLiValidatorException;
import de.jl.yasli.utils.UniCodeChars;
import de.jl.yasli.validator.IMyLiValidator;
import de.jl.yasli.validator.MyLiNumberValidator;
import de.jl.yasli.validator.MyLiTextValidator;

public class MyLiValidator {

	public static final Character SINGLEQUOTES = 0x0027; //"\u0027";
	public static final Character QUOTES = 0x0022;//"u0022";

	private List<MyLiVar> vars = null;
	
	public MyLiValidator(List<MyLiVar> vars) {
		this.vars = vars;
	}

	public boolean validate(List<MyLiItem> items) throws MyLiValidatorException {
		for (Integer i=0; i<items.size(); i++) {
			if (! validate(items.get(i.intValue()))) {
				return false;
			}
		}
		return true;
	}
	public boolean validate(MyLiItem item) throws MyLiValidatorException {
		if(item.getType()==MyLiItem.VARIABLE) {
			MyLiVar var = (MyLiVar) item;
			if (!MyLiTextValidator.getValidator().isValid(var.getName(), IMyLiValidator.VARIABLENAME)) {
				throw new MyLiValidatorException("Invalid VariableName: ["+var.getName()+"]"); 
			}
			
			if(var.getItem().getType()==MyLiItem.CONSTANT) {
				if(! MyLiTextValidator.getValidator().isValid(var.getItem().getValue(), IMyLiValidator.CONSTANT)) {
					throw new MyLiValidatorException("Invalid format for Constants: ["+var.getItem().getValue()+"]"); 
				}
			} else if(var.getItem().getType()==MyLiItem.TEXT) {
				if (! MyLiTextValidator.getValidator().isValid(var.getItem().getValue(), IMyLiValidator.TEXT)) {
					throw new MyLiValidatorException("Invalid format for Textparts: ["+var.getItem().getValue()+"]"); 
				}
			} else if(var.getItem().getType()==MyLiItem.NUMBER) {
				if (! MyLiNumberValidator.getValidator().isValid(var.getItem().getValue(), IMyLiValidator.GERMAN)) {
					throw new MyLiValidatorException("Invalid format for Numbers: ["+var.getItem().getValue()+"]"); 
				}
			} 
		} else if(item.getType()==MyLiItem.TEXT ) {
			if (! MyLiTextValidator.getValidator().isValid(item.getValue(), IMyLiValidator.TEXT)) {
				throw new MyLiValidatorException("Invalid format for Textparts: ["+item.getValue()+"]"); 
			}
		} else if(item.getType()==MyLiItem.CONSTANT ) {
			if (! MyLiTextValidator.getValidator().isValid(item.getValue(), IMyLiValidator.CONSTANT)) {
				throw new MyLiValidatorException("Invalid format for Constants: ["+item.getValue()+"]"); 
			}
		} else if(item.getType()==MyLiItem.NUMBER) {
			if (! MyLiNumberValidator.getValidator().isValid(item.getValue(), IMyLiValidator.GERMAN)) {
				throw new MyLiValidatorException("Invalid format for Numbers: ["+item.getValue()+"]"); 
			}
		} 
		return true;
	}
	
	public static Integer getVarType(String var) {
		char ch = var.charAt(0);
		if (Character.isDigit(ch)) {
			return MyLiItem.NUMBER;		
		} else if (Character.isLetter(ch)) {
			return MyLiItem.TEXT;		
		} else if (UniCodeChars.isBracket(ch)) {
			return MyLiItem.BRACKET;		
		} else if (UniCodeChars.isOperator(ch)) {
			return MyLiItem.OPERATOR;		
		} else if (UniCodeChars.isEqual(UniCodeChars.QUOTATIONMARK, ch)) {
			return MyLiItem.CONSTANT;		
		} else if (UniCodeChars.isEqual(UniCodeChars.APOSTROPHE, ch)) {
			return MyLiItem.CONSTANT;		
		} else {
			return MyLiItem.UKN;		
		}
	}

}
