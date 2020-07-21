package de.jl.yasli.items;

import de.jl.yasli.MyLiTask;

public class MyLiOperator extends MyLiItem {

	private Integer p = NOTSET;
	
	public MyLiOperator(Integer priority, String value, Integer type) {
		super(value, type);
		this.p = priority;
	}
	
	public Character getOperatorValue() {
		return super.getValue().charAt(0);
	}

	@Override
	public Boolean isExponent() {
		return (MyLiTask.EXPONENT == this.getPriority());
	}
	@Override
	public Boolean isLineOperation() {
		return (MyLiTask.LINEOPERATION == this.getPriority());
	}
	@Override
	public Boolean isPunctuation() {
		return (MyLiTask.PUNCTUATION == this.getPriority());
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
