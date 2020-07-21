package de.jl.yasli.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.jl.yasli.items.MyLiBracket;
import de.jl.yasli.items.MyLiConst;
import de.jl.yasli.items.MyLiItem;
import de.jl.yasli.items.MyLiOperator;
import de.jl.yasli.items.MyLiVar;

public class MyLiTaskReader extends MyLiReader {
	
	public MyLiTaskReader(File file) {
		super(file);
	}
		
	public List<List<MyLiItem>> readFile() {
		Integer index = 0;
		List<List<MyLiItem>> tasks = new ArrayList<List<MyLiItem>>();
		try {
			FileReader fr = new FileReader(this.f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					//vars.addAll(lineReader(line));
					tasks.add(lineReader(line));
					index++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tasks;
	}

	private static List<MyLiItem> lineReader(String line) {
		Character c0 = null;
		Integer i = 0;
		MyLiItem item = null;
		Integer itemType = MyLiItem.NOTSET;
		Integer level = -1;
		List<MyLiItem> list = new ArrayList<MyLiItem>();
		Boolean startingMark = false;
		String value = "";
		while(i<line.length()) {
			c0 = line.charAt(i);
			/*
			 * Idee:
			 * Schrittweise auseinander nehmen der Eingabe und Klassifizieren.
			 * Ein Operator beendet immer eine Constante oder eine Variable.
			 */
			if (UniCodeChars.isOperator(c0)) {
				if (UniCodeChars.isBracket(c0)) {
					/*
					 * Wenn Value nicht leer, dann als eigenes Item abspeichern.
					 */
					if (!value.isEmpty()) {
						if (itemType == MyLiItem.NUMBER) {
							item = new MyLiItem(value, itemType);
						} else {
							item = new MyLiItem(value, itemType);
						}
						list.add(item);
						itemType = MyLiItem.NOTSET ;
						value = "";
					}
					/*
					 * F�r geschachtelte Klammern ist das Level notwendig.
					 */
					if (UniCodeChars.isOpeningBracket(c0)) {
						level++;
						item = new MyLiBracket(MyLiOperator.BRACKET, c0.toString(), MyLiItem.BRACKET, level);
					} else {
						item = new MyLiBracket(MyLiOperator.BRACKET, c0.toString(), MyLiItem.BRACKET, level);
						level--;
					} 
					list.add(item);
				}
				else if (UniCodeChars.isMathematicalOperator(c0)) {
					if (!value.isEmpty()) {
						if (itemType == MyLiItem.NUMBER) {
							item = new MyLiItem(value, itemType);
						} else {
							item = new MyLiVar(value, value, itemType);
						}
						list.add(item);
						itemType = MyLiItem.NOTSET ;
						value = "";
					}
					if (UniCodeChars.isEqual(UniCodeChars.PLUS, c0)) {
						item = new MyLiOperator(MyLiOperator.LINEOPERATION, c0.toString(), MyLiItem.OPERATOR);
					}
					if (UniCodeChars.isEqual(UniCodeChars.MINUS, c0)) {
						item = new MyLiOperator(MyLiOperator.LINEOPERATION, c0.toString(), MyLiItem.OPERATOR);
					}
					if (UniCodeChars.isEqual(UniCodeChars.ASTERISK, c0)) {
						item = new MyLiOperator(MyLiOperator.PUNCTUATION , c0.toString(), MyLiItem.OPERATOR);
					}
					if (UniCodeChars.isEqual(UniCodeChars.SOLIDUS, c0)) {
						item = new MyLiOperator(MyLiOperator.PUNCTUATION, c0.toString(), MyLiItem.OPERATOR);
					}
					list.add(item);
				}
				/*
				 * Ein Anf�hrungszeichen kann Anfang und Ende markieren.
				 */
				else if (UniCodeChars.isEqual(UniCodeChars.APOSTROPHE, c0) || 
					 UniCodeChars.isEqual(UniCodeChars.QUOTATIONMARK, c0)) {
						value = value.concat(c0.toString());
						if(!startingMark) {
							startingMark = true;
						} else {
							item = new MyLiConst(value, itemType);
							list.add(item);
							itemType = MyLiItem.NOTSET ;
							value = "";							
							startingMark = false;
						}
				}
			/*
			 * Ein String kann mehr als ein Zeichen lang sein. 
			 */
			} else if (Character.isLetter(c0)) {
				value = value.concat(c0.toString());
				if (startingMark) {
					itemType = MyLiItem.CONSTANT ;
				} else {
					itemType = MyLiItem.VARIABLE ;
				}
			} else if (UniCodeChars.isEqual(UniCodeChars.APOSTROPHE, c0) || UniCodeChars.isEqual(UniCodeChars.QUOTATIONMARK, c0)) {
				value = value.concat(c0.toString());
				if(startingMark) {
					item = new MyLiItem(value, itemType);
					list.add(item);
					value = "";
					startingMark = false;
					itemType = MyLiItem.NOTSET;
				}
				else {
					startingMark = true;
					itemType = MyLiItem.CONSTANT ;
				}
			} else if (Character.isDigit(c0)) {
				value = value.concat(c0.toString());
				if (startingMark) {
					itemType = MyLiItem.CONSTANT;
				} else {
					itemType = MyLiItem.NUMBER;
				}
			} else if (UniCodeChars.isPunctiation(c0)) {
				value = value.concat(c0.toString());
				itemType = MyLiItem.VARIABLE ;
			} else if (UniCodeChars.isBlank(c0)) {
				if (startingMark) {
					value = value.concat(c0.toString());
					itemType = MyLiItem.CONSTANT ;	
				}
			} 
			i++;
		}
		if (!value.isEmpty()) {
			if (itemType == MyLiItem.NUMBER) {
				item = new MyLiItem(value, itemType);
			} else {
				item = new MyLiVar(value, value, itemType);
			}
			list.add(item);
			itemType = MyLiItem.NOTSET ;
			value = "";
		}
		return list;
	}

}
