package de.jl.yasli.items;

import de.jl.yasli.utils.MyLiUtils;

public class MyLiVar extends MyLiItem {

	private String n = null;
	private MyLiItem item = null;
	
	public MyLiVar(String name, String value, Integer type) {
		super(value, MyLiItem.VARIABLE);
		this.n = name;
		item = new MyLiItem(value, type);
	}
	
	public String getName() {
		return n;
	}
	@Override
	public String getValue() {
		if (item.getType()==MyLiItem.CONSTANT)
			return MyLiUtils.trimmMarks(item.getValue());
		else 
			return item.getValue();
	}
	public MyLiItem getItem() {
		return this.item;
	}
	public Boolean compareName(String name) {
		return name.equals(this.getName());
	}
	
	public String toString() {
		String s = "";
		s = s.concat(super.toString());
		if (!this.getName().isEmpty()) {
			s = s.concat("Name: ").concat(this.getName()).concat(" ");
		}
		return s;
	}
}
