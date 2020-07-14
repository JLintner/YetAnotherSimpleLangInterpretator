package de.jl.myli.items;

public class MyLiOperator extends MyLiItem {

	public static final Integer BRACKET = 1;
	public static final Integer EXPONENT = 2;
	public static final Integer PUNCTUATION = 3;
	public static final Integer LINE = 4; 
	public static final Integer NOTSET = 99;		
	
	private Integer p = NOTSET;
	
	public MyLiOperator(Integer priority, String value, Integer type) {
		super(value, type);
		this.p = priority;
	}
	
	public Integer getPriority() {
		return p;
	}
	
	public String toString() {
		String s = "";
		s = s.concat(super.toString());
		s = s.concat("Priority: ").concat(Integer.toString(this.getPriority())).concat(" ");
		return s;
	}
}
