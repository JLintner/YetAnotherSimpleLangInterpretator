package de.jl.myli;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import de.jl.myli.items.MyLiItem;
import de.jl.myli.items.MyLiVar;

public class MyLiTask {
	
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
	
	public void runTasks() {
		Iterator varIter = vars.listIterator();
		Iterator taskIter = tasks.listIterator(); 
	}
	
	public String toString() {
		String s = "";
		if (this.vars != null) {
			Iterator<MyLiVar> iter = this.vars.listIterator();
			while(iter.hasNext()) {
				s = s.concat(iter.next().toString()).concat(System.lineSeparator());
			}
			s = s.concat(System.lineSeparator());
		}
		if (this.tasks!= null) {
			Iterator<List<MyLiItem>> tasks = this.tasks.listIterator();
			while(tasks.hasNext()) {
				List<MyLiItem> li = tasks.next();
				Iterator<MyLiItem> iter = li.listIterator();
				while(iter.hasNext()) {
					s = s.concat(iter.next().toString()).concat(System.lineSeparator());
				}
				s = s.concat(System.lineSeparator());
			}
		}
		return s;
	}
}
