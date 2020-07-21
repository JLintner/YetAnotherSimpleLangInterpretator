package de.jl.yasli.items;

import de.jl.yasli.utils.MyLiUtils;
import de.jl.yasli.utils.MyLiValidatorException;
import de.jl.yasli.utils.UniCodeChars;

public class MyLiItem {

	public static final Integer BRACKET = 11;
	public static final Integer CONSTANT = 5;
	public static final Integer EXPONENT = 12;
	public static final Integer LINEOPERATION = 14; // Strichrechnung
	public static final Integer NOTSET = -1;
	public static final Integer NUMBER = 4;
	public static final Integer OPERATOR = 1;
	public static final Integer PUNCTUATION = 13;
	public static final Integer TEXT = 3;
	public static final Integer VARIABLE = 2;
	public static final Integer UKN = 0;
	
	private String v = null;
	private Integer t = MyLiItem.NOTSET;

	public MyLiItem(String value, Integer type) {
		this.t = type;
		this.v = value;

	}
	public MyLiItem(Double value, Integer type) {
		this.v = cleanNumber(value);
		this.t = type;
	}

	public void setValue(String v) {
		this.v = v;
		if (this.t == MyLiItem.NUMBER) {
			try {
				this.v = cleanNumber(MyLiUtils.convertToDouble(v));
			} catch (MyLiValidatorException e) {
				this.setType(MyLiItem.TEXT);
			}
		}
	}
	public void setValue(Double v) {
		if (this.t == MyLiItem.NUMBER) {
			this.setValue(String.valueOf(v));
		}
	}
	public void setType(Integer t) {
		this.t = t;
	}
	public String getValue() {
		return v;
	}
	public Double getNumberValue() throws MyLiValidatorException {
		if(isNumber()) {
			return MyLiUtils.convertToDouble(v);
		}
		else
			throw new MyLiValidatorException("This Item is not of typ NUMBER: ["+this.v+"]");
	}
	public Integer getType() {
		return t;
	}
	public Boolean isBracket() {
		return this.t==MyLiItem.BRACKET;
	}
	public Boolean isConstant() {
		return this.t==MyLiItem.CONSTANT;
	}
	public Boolean isExponent() {
		return this.t==MyLiItem.EXPONENT;
	}
	public Boolean isLineOperation() {
		return this.t==MyLiItem.LINEOPERATION;
	}
	public Boolean isNumber() {
		return this.t==MyLiItem.NUMBER;
	}
	public Boolean isOperator() {
		return this.t==MyLiItem.OPERATOR;
	}
	public Boolean isPunctuation() {
		return this.t==MyLiItem.PUNCTUATION;
	}
	public Boolean isText() {
		return this.t==MyLiItem.TEXT;
	}
	public Boolean isVariable() {
		return this.t==MyLiItem.VARIABLE;
	}
	private String cleanNumber(Double number) {
		String s = String.valueOf(number.doubleValue());
		Double floor = Math.floor(number);
		if (number - floor == 0) {
			s = s.substring(0,s.lastIndexOf(UniCodeChars.getChar(UniCodeChars.FULLSTOP)));
		}
		return s;
	}
	public String getValueTrimmed() {
		return MyLiUtils.trimmMarks(this.getValue());
	}

	public String toString() {
		String s = "";
		s = s.concat("Value: ").concat(this.getValue()).concat(" ");
		s = s.concat("Type: ").concat(Integer.toString(this.getType())).concat(" ");
		return s;
	}
}
