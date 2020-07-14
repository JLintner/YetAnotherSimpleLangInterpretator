package de.jl.myli.items;

public class MyLiVar extends MyLiItem {

	private String n = null;
	
	public MyLiVar(String name, String value, Integer type) {
		super(value ,type);
		this.n = name;
	}
	
	public String getName() {
		return n;
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
