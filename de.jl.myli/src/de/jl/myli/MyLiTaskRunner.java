package de.jl.myli;

import java.util.ArrayList;
import java.util.List;

import de.jl.myli.constants.UniCodeChars;
import de.jl.myli.items.MyLiBracket;
import de.jl.myli.items.MyLiItem;
import de.jl.myli.items.MyLiOperator;
import de.jl.myli.items.MyLiVar;

public class MyLiTaskRunner {
	
	public MyLiTask t = null;
	
	public MyLiTaskRunner(MyLiTask task) {
		this.t = task;
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
			return this.t.getTasks().size();
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
		for(Integer i = 0; i<this.count(); i++) {
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
		List<MyLiItem> left_stack = new ArrayList<MyLiItem>();
		List<MyLiOperator> operator_stack = new ArrayList<MyLiOperator>();
		List<MyLiItem> right_stack = new ArrayList<MyLiItem>();
		MyLiItem result = null;
		if (index < this.count()) {
			List<MyLiVar> vars = this.t.getVars();
			for (Integer i = 0; i < this.count(); i++) {
				List<MyLiItem> items = this.t.getTaskItems(i);
					result  = runSubtask(items,  0, items.size(), MyLiTask.BRACKET).get(0);
					s = s.concat(result.getValue());
			}		
		} else {
			throw new IndexOutOfBoundsException("There are not enough task available: index: [" + index + "]");
		}
		return s;
	}
	
	/**
	 * Reucurision: Calculates the result by evaluating inner subtasks, providing the result upwise.
	 * @param items, the whole task, splited into items
	 * @param startingIndex, the index of the current subtask
	 * @param taskType, tell, if the current subtask is a punctuation, bracket or something else
	 * @return MyLiItem, an adapted Task where inner results are calculated.
	 */
	private List<MyLiItem> runSubtask(List<MyLiItem> items, Integer startingIndex, Integer stopIndex, Integer taskType) {
		List<MyLiItem> result = new ArrayList<MyLiItem>();
		MyLiItem item = null;
		MyLiItem left = null;
		MyLiOperator operator = null;
		MyLiBracket bracket = null;
		Integer bracketLevel = -1;
		MyLiOperator next_operator = null;
		Integer lastIndex = -1;
		Integer newStart = -1;
		Integer newStop = -1;
		MyLiItem right = null;
		for (Integer i=startingIndex; i<stopIndex; i++) {
			item = items.get(i);
			
			// if bracket, then a subtask may opened or closed
			if (item.isBracket()) {
				bracket = (MyLiBracket)item;
				if (UniCodeChars.isOpeningBracket(bracket.getOperatorValue())) {
					// if bracket level is not set, then a subtask is opened
					if(bracketLevel == -1) {
						bracketLevel = bracket.getLevel();
						newStart = i+1;
						left = null;
					} 				
				} else if (UniCodeChars.isClosingBracket(bracket.getOperatorValue())) {
					// check if bracket is in right level
					if(bracketLevel == bracket.getLevel()) {
						newStop = i;
						// the shortened itemslist is given back.
						result = runSubtask(items, newStart, newStop, MyLiTask.BRACKET);
						right = result.get(0);
						i = newStart;
						bracketLevel = -1;
						bracket = null;
						stopIndex = items.size()- newStop;
					}
				}
			} else if (item.isOperator()) {
				if (operator == null) {
					operator = (MyLiOperator)item;				
				}
			} else if (item.isNumber() || item.isText()) {
				if (left == null) {
					left = item;
				} else if (right == null) {
					right = item;
				}
			}
			
			if (left != null && right != null) {
				left = doOperation(left, operator, right);
				result.add(left);
				right = null;
				operator = null;
			}
			

		}
		
		
/*
		if (item.isOperator()) {
			if (current_operator == null) {
			}		
		} else if (item.isVariable()) {
			if(containsVariable(item.getValue(), vars)) {
				var = getVariable(item.getValue(), vars);
				next_item = new MyLiItem(var.getValue(), var.getType());
			}
		} else if (item.isNumber()) {
			next_item = item;
			if (current_item == null) {
				current_item = next_item;
			} else {
				current_item = doOperation(current_item, current_operator, next_item);
			}
		} else if (item.isText()) {
		}  
*/
		
		return result;
	}
	
	private MyLiItem doOperation(MyLiItem left, MyLiOperator operator, MyLiItem right) {
		if(left.isText()) {
			return doConcatention(left.getValue(), operator.getOperatorValue(), right.getValue());  
		} else {
			if(right.isText()) {
				return doConcatention(left.getValue(), operator.getOperatorValue(), right.getValue());  
			} else {
				return doMathematicalOperation(left.getNumberValue(), operator.getOperatorValue(), right.getNumberValue());
			}
		}
	}
		
	private MyLiItem doConcatention(String left, Character operator, String right) throws UnsupportedOperationException {
		if(UniCodeChars.isEqual(UniCodeChars.PLUS, operator)) {
			return new MyLiItem(left.concat(right), MyLiItem.TEXT);
		} else {
			throw new UnsupportedOperationException("The operator [" + operator + "] is not supported.");
		}
	}
	
	private MyLiItem doMathematicalOperation(Double left, Character operator, Double right) throws UnsupportedOperationException {
		if(UniCodeChars.isEqual(UniCodeChars.PLUS, operator)) {
			return new MyLiItem(left + right, MyLiItem.NUMBER);
		} else if(UniCodeChars.isEqual(UniCodeChars.MINUS, operator)) {
			return  new MyLiItem(left - right, MyLiItem.NUMBER);
		} else if(UniCodeChars.isEqual(UniCodeChars.ASTERISK, operator)) {
			return  new MyLiItem(left * right, MyLiItem.NUMBER);
		} else if(UniCodeChars.isEqual(UniCodeChars.SOLIDUS, operator)) {
			if (right != 0)
				return  new MyLiItem(left / right, MyLiItem.NUMBER);
			else
				throw new UnsupportedOperationException("Divison by zero is not allowed.");
		} else {
			throw new UnsupportedOperationException("The operator [" + operator + "] is not supported.");
		}
	}
}
