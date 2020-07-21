package de.jl.yasli.items;

import de.jl.yasli.utils.UniCodeChars;

public class MyLiBracket extends MyLiOperator {

	private Integer l = -1;
	
	public MyLiBracket(Integer priority, String value, Integer type, Integer level) {
		super(priority, value ,type);
		this.l = level;
	}
	
	@Override
	public Boolean isBracket() {
		return true;
	}
	public Boolean isOpeningBracket() {
		return UniCodeChars.isOpeningBracket(this.getOperatorValue());
	}
	public Boolean isClosingBracket() {
		return UniCodeChars.isClosingBracket(this.getOperatorValue());
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
