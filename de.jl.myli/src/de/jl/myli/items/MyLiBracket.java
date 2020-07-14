package de.jl.myli.items;

public class MyLiBracket extends MyLiOperator {

	private Integer l = -1;
	
	public MyLiBracket(Integer priority, String value, Integer type, Integer level) {
		super(priority, value ,type);
		this.l = level;
	}
	
	public Integer getLevel() {
		return l;
	}
	public String toString() {
		String s = "";
		s = s.concat(super.toString());
		s = s.concat("Level: ").concat(Integer.toString(this.getLevel())).concat(" ");
		return s;
	}
}
