package de.jl.yasli;

import java.util.Iterator;
import java.util.List;

import de.jl.yasli.items.MyLiItem;
import de.jl.yasli.items.MyLiVar;

public class MyLiTask {
	
	public static final Integer BRACKET = 11;
	public static final Integer EXPONENT = 12;
	public static final Integer LINEOPERATION = 14; // Strichrechnung
	public static final Integer NOTSET = -1;
	public static final Integer PUNCTUATION = 13;
	public static final Integer UKN = 0;
	
	private List<List<MyLiItem>> tasks = null;
	private List<MyLiVar> vars = null;
	
	public MyLiTask(List<List<MyLiItem>> tasks, List<MyLiVar> vars) {
		this.tasks = tasks;
		this.vars = vars;
	}

	public List<MyLiItem> getTaskItems(Integer index) {
		return tasks.get(index);
	}
	public List<List<MyLiItem>> getTasks() {
		return tasks;
	}
	
	public List<MyLiVar> getVars() {
		return vars;
	}
	
	public String toString() {
		String s = "";
		if (this.vars != null) {
			Iterator<MyLiVar> iter = this.vars.listIterator();
			s = s.concat("Variables:").concat(System.lineSeparator());
			while(iter.hasNext()) {
				s = s.concat(iter.next().toString()).concat(System.lineSeparator());
			}
			s = s.concat(System.lineSeparator());
		}
		if (this.tasks!= null) {
			Iterator<List<MyLiItem>> tasks = this.tasks.listIterator();
			s = s.concat("Tasks:").concat(System.lineSeparator());
			while(tasks.hasNext()) {
				List<MyLiItem> li = tasks.next();
				Iterator<MyLiItem> iter = li.listIterator();
				while(iter.hasNext()) {
					s = s.concat(iter.next().getValue());
				}
				s = s.concat(System.lineSeparator());
			}
		}
		return s;
	}
}
