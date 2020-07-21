package de.jl.yasli;

import java.util.ArrayList;
import java.util.List;

import de.jl.yasli.items.MyLiBracket;
import de.jl.yasli.items.MyLiItem;
import de.jl.yasli.items.MyLiOperator;
import de.jl.yasli.items.MyLiVar;
import de.jl.yasli.utils.MyLiUtils;
import de.jl.yasli.utils.MyLiValidatorException;
import de.jl.yasli.utils.UniCodeChars;
import de.jl.yasli.validator.IMyLiValidator;

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
	public String runTasks() throws MyLiValidatorException {
		String s = "";
		System.out.println(this.t.toString().concat(System.lineSeparator()));
		System.out.println("Results:");
		for(Integer i = 0; i<this.count(); i++) {
			try {
				s = s.concat(runTask(i)).concat(System.lineSeparator());
			} catch (IndexOutOfBoundsException e) {
				s = s.concat("Task with index ["+i+"] was not found.");
			} catch (MyLiValidatorException e) {
				s = s.concat("Error during validation: " + e.getMessage()).concat(System.lineSeparator());
			}
		}
		return s;
	}
	
	/**
	 * Runs a specific task
	 * @param index, index of a specific task in list
	 * @return
	 */
	public String runTask(Integer index) throws MyLiValidatorException {
		String s = "";
		List<MyLiItem> results = new ArrayList<MyLiItem>();
		if (index < this.count()) {
			List<MyLiItem> items = this.t.getTaskItems(index);
			results = runSubtask(items);
			s = s.concat(itemsToString(results));	
		} else {
			throw new MyLiValidatorException("There are not enough task available: index: [" + index + "]");
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
	private List<MyLiItem> runSubtask(List<MyLiItem> items) throws MyLiValidatorException {
		List<MyLiItem> result = new ArrayList<MyLiItem>();
		MyLiItem item = null;
		MyLiItem left = null;
		MyLiItem right = null;
		MyLiBracket bracket = null;
		MyLiOperator operator = null;

		Integer lastOpeningBracket = null;
		Integer lastClosingBracket = null;

		Integer openingBracketCount = 0;
		if (items.size() > 1) {
			while (items.size() > 1) {
				// Remove Brackets
				if (containsBracket(items)) {
					for (Integer i=0; i<items.size(); i++) {
						item = items.get(i);
						if (item.isBracket()) {
							bracket = (MyLiBracket)item;
							if(bracket.isOpeningBracket()) {
								lastOpeningBracket = i+1;
								openingBracketCount++;
							}
							if(bracket.isClosingBracket()) {
								lastClosingBracket = i;
								result = runSubtask(items.subList(lastOpeningBracket, lastClosingBracket)); 
								items.addAll(lastOpeningBracket-1, result);
								
								for (Integer j = lastOpeningBracket; j < lastClosingBracket; j++) {
									items.remove(lastOpeningBracket.intValue());
								}
								i = -1;
							}
						}
					}
					// Calculate
				} else if(containsPunctuation(items)) {
					for (Integer i=0; i<items.size(); i++) {
						item = items.get(i);
						if (item.isOperator()) {
							operator = (MyLiOperator)item;
							if (operator.isPunctuation()) {
								left = items.get(i-1);
								if (items.size( )< i+1) {
									// throw error. invalid format
								}
								right = items.get(i+1);
								left = doOperation(left, operator, right);
								right = null;
								items.set(i-1, left);
								
								for (Integer j = i; j <= i+1; j++) {
									items.remove(i.intValue());
								}
								i = i - 1;
							}
						}
					}
				} else if (containsLineOperation(items)) {
					for (Integer i=0; i<items.size(); i++) {
						item = items.get(i);
						if (item.isOperator()) {
							operator = (MyLiOperator)item;
							if (operator.isLineOperation()) {
								left = items.get(i-1);
								if (items.size( )< i+1) {
									// throw error. invalid format
								}
								right = items.get(i+1);
								left = doOperation(left, operator, right);
								right = null;
								items.set(i-1, left);
								
								for (Integer j = i; j <= i+1; j++) {
									items.remove(i.intValue());
								}
								i = i - 1;
							}
						}
					}
				} 
			}
		} else {
			MyLiVar var = null;
			if(items.get(0).isVariable()) {
				if (containsVariable(((MyLiVar)items.get(0)).getName(), this.t.getVars())) {
					var = getVariable(((MyLiVar)items.get(0)).getName(), this.t.getVars());
					items.set(0, ((MyLiVar)items.get(0)).getItem());
				} else {
					throw new MyLiValidatorException("Variable [ " + ((MyLiVar)items.get(0)).getName() +" ] could not be found.");
				}
			}
		}
		return items;
	}

	private Boolean containsBracket(List<MyLiItem> items) {
		for(Integer i=0; i<items.size(); i++) {
			if(items.get(i).isBracket())
				return true;
		}
		return false;
	}
	private Boolean containsPunctuation(List<MyLiItem> items) {
		for(Integer i=0; i<items.size(); i++) {
			if(items.get(i).isOperator()) {
				if(((MyLiOperator)items.get(i)).isPunctuation())
					return true;
			}
		}
		return false;
	}
	private Boolean containsLineOperation(List<MyLiItem> items) {
		for(Integer i=0; i<items.size(); i++) {
			if(items.get(i).isOperator()) {
				if(((MyLiOperator)items.get(i)).isLineOperation())
					return true;
			}
		}
		return false;
	}
	
	private MyLiItem doOperation(MyLiItem left, MyLiOperator operator, MyLiItem right) throws MyLiValidatorException {
		MyLiVar var = null;
		if(left.isVariable()) {
			if (containsVariable(((MyLiVar)left).getName(), this.t.getVars())) {
				var = getVariable(((MyLiVar)left).getName(), this.t.getVars());
				left = var.getItem();
			} else {
				throw new MyLiValidatorException("Variable [ " + ((MyLiVar)left).getName() +" ] could not be found.");
			}
		}
		if (right.isVariable()) {
			if (containsVariable(((MyLiVar)right).getName(), this.t.getVars())) {
				var = getVariable(((MyLiVar)right).getName(), this.t.getVars());
				right = var.getItem();
			} else {
				throw new MyLiValidatorException("Variable [ " + ((MyLiVar)right).getName() +" ] could not be found.");
			}
	
		}
		
		if(left.isText() ||  left.isConstant()) {
			return doConcatention(left.getValueTrimmed(), operator.getOperatorValue(), right.getValueTrimmed());  
		} else {
			if(right.isText() ||  right.isConstant()) {
				return doConcatention(left.getValueTrimmed(), operator.getOperatorValue(), right.getValueTrimmed());  
			} else {
				try {
					return doMathematicalOperation(left.getNumberValue(), operator.getOperatorValue(), right.getNumberValue());
				} catch (MyLiValidatorException e) {
					throw e;
				} 
			}
		}
	}
		
	private MyLiItem doConcatention(String left, Character operator, String right) throws MyLiValidatorException {
		if(UniCodeChars.isEqual(UniCodeChars.PLUS, operator)) {
			return new MyLiItem(left.concat(right), MyLiItem.TEXT);
		} else {
			throw new MyLiValidatorException("The operator [" + operator + "] in " + left + " " + operator.toString() + " " + right + " is not supported.");
		}
	}
	
	private MyLiItem doMathematicalOperation(Double left, Character operator, Double right) throws MyLiValidatorException {
		if(UniCodeChars.isEqual(UniCodeChars.PLUS, operator)) {
			return new MyLiItem(MyLiUtils.converNumberformat(left + right, IMyLiValidator.GERMAN), MyLiItem.NUMBER);
		} else if(UniCodeChars.isEqual(UniCodeChars.MINUS, operator)) {
			return  new MyLiItem(MyLiUtils.converNumberformat(left - right, IMyLiValidator.GERMAN), MyLiItem.NUMBER);
		} else if(UniCodeChars.isEqual(UniCodeChars.ASTERISK, operator)) {
			return  new MyLiItem(MyLiUtils.converNumberformat(left * right, IMyLiValidator.GERMAN), MyLiItem.NUMBER);
		} else if(UniCodeChars.isEqual(UniCodeChars.SOLIDUS, operator)) {
			if (right != 0)
				return  new MyLiItem(MyLiUtils.converNumberformat(left / right, IMyLiValidator.GERMAN), MyLiItem.NUMBER);
			else
				throw new MyLiValidatorException("Divison by zero is not allowed.");
		} else {
			throw new MyLiValidatorException("The operator [" + operator + "] in " + left.toString() + " " + operator.toString() + " " + right.toString() + " is not supported.");
		}
	}
	private String itemsToString(List<MyLiItem> items) {
		String s = "";
		for(Integer i = 0; i< items.size(); i++) {
			s = s.concat(items.get(i.intValue()).getValue());
		}
		return s;
		
	}
}
