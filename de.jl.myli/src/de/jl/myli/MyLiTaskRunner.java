package de.jl.myli;

import java.util.List;

import de.jl.myli.items.MyLiItem;
import de.jl.myli.items.MyLiVar;

public class MyLiTaskRunner {

	public List<MyLiTask> t = null;
	
	public MyLiTaskRunner(List<MyLiTask> tasks) {
		this.t = tasks;
	}
	/**
	 * Checks, if the list contains a variable with the name <i>varName</i> 
	 * @param varName, the varName you are looking for
	 * @param vars, the list of known variable
	 * @return Boolean, true, if the list contains a variable with name <i>varName</i>, false if not.
	 */
	private Boolean containsVariable(String varName, List<MyLiVar> vars) {
		Boolean result = false;
		for (Integer i=0; i < vars.size(); i++) {
			if (vars.get(i).compareName(varName)) {
				result = true;
				break;
			}
		}
		return result;
	}
	/**
	 * Returns the number of the tasks
	 * @return Integer, number of tasks
	 */
	public Integer count() {
		if (this.t != null) {
			return this.t.size();
		} else {
			return -1;
		}
	}
	/**
	 * Returns the MyLiVar with the name
	 * @param varName, name of variable to look for
	 * @param vars, list of variables (MyLiVar)
	 * @return MyLiVar, the variable with the given name
	 */
	private MyLiVar getVariable(String varName, List<MyLiVar> vars) {
		for (Integer i=0; i < vars.size(); i++) {
			if (vars.get(i).compareName(varName)) {
				return vars.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Runs all Task in list
	 * @return String, result of all tasks
	 */
	public String runTasks() {
		String s = "";
		for(Integer i = 0; i<t.size(); i++) {
			try {
				s = s.concat(runTask(i)).concat(System.lineSeparator());
			} catch (IndexOutOfBoundsException e) {
				s = s.concat("Task with index ["+i+"] was not found.");
			}
		}
		return s;
	}
	
	/**
	 * Runs a specific task
	 * @param index, index of a specific task in list
	 * @return
	 */
	public String runTask(Integer index) throws IndexOutOfBoundsException {
		MyLiVar var = null;
		String s = "";
		String tmp_text = "";
		Double tmp_number = -1d;
		Double current_number = -1d;
		if (index < this.count()) {
			MyLiTask task = t.get(index);
			List<MyLiVar> vars = task.getVars();
			for (Integer i = 0; i < task.getTaskItems(i).size(); i++) {
				List<MyLiItem> items = task.getTaskItems(i);
				for (Integer j=0; j < items.size(); j++) {
					MyLiItem item = items.get(j);
					if (item.isNumber()) {
						if (current_number == -1d) {
							current_number = Double.parseDouble(item.getValue());
						}
					} else if (item.isOperator()) {
						
					} else if (item.isText()) {
						
					} else if (item.isVariable()) {
						if(containsVariable(item.getValue(), vars)) {
							var = getVariable(item.getValue(), vars);
						}
					}
				}			
			}		
		} else {
			throw new IndexOutOfBoundsException("There are not enough task available: index: [" + index + "]");
		}
		return s;
	}

}
