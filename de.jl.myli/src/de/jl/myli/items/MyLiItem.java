package de.jl.myli.items;

public class MyLiItem {

	public static final Integer NOTSET = -1;
	public static final Integer UKN = 0;
	public static final Integer OPERATOR = 1;
	public static final Integer VARIABLE = 2;
	public static final Integer TEXT = 3;
	public static final Integer NUMBER = 4;
	
	private String v = null;
	private Integer t = MyLiItem.NOTSET;
	
	public MyLiItem(String value, Integer type) {
		this.v = value;
		this.t = type;
	}
	
	public String getValue() {
		return v;
	}
	public Integer getType() {
		return t;
	}
	public boolean isOperator() {
		return this.t==MyLiItem.OPERATOR;
	}
	public boolean isVariable() {
		return this.t==MyLiItem.VARIABLE;
	}
	public boolean isText() {
		return this.t==MyLiItem.TEXT;
	}
	public boolean isNumber() {
		return this.t==MyLiItem.NUMBER;
	}
	public String toString() {
		String s = "";
		s = s.concat("Value: ").concat(this.getValue()).concat(" ");
		s = s.concat("Type: ").concat(Integer.toString(this.getType())).concat(" ");
		return s;
	}
}
