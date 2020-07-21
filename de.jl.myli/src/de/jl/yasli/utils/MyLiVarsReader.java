package de.jl.yasli.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.jl.yasli.MyLiValidator;
import de.jl.yasli.items.MyLiItem;
import de.jl.yasli.items.MyLiVar;
import de.jl.yasli.validator.IMyLiValidator;
import de.jl.yasli.validator.MyLiNumberValidator;
import de.jl.yasli.validator.MyLiTextValidator;

public class MyLiVarsReader extends MyLiReader{

	public MyLiVarsReader(File file) {
		super(file);
	}
	
	public List<MyLiVar> readFile() throws MyLiValidatorException {
		String varName = null;
		String varVal = null;
		Integer varType = MyLiItem.NOTSET;

		List<MyLiVar> vars = new ArrayList<MyLiVar>();
		try {
			FileReader fr = new FileReader(this.f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					varName = line.substring(0, line.indexOf(0x003d));
					if (!MyLiTextValidator.getValidator().isValid(varName, IMyLiValidator.VARIABLENAME)) {
						throw new MyLiValidatorException("this variable name is illegal: " + varName);
					}					
					varVal = line.substring(line.indexOf(0x003d)+1, line.length());
					varType = MyLiValidator.getVarType(varVal);		
					if (varType == MyLiItem.NUMBER) {
						if (!MyLiNumberValidator.getValidator().isValid(varVal, IMyLiValidator.GERMAN)) {
							throw new MyLiValidatorException("this variable value is illegal: not in German numbar format" + varVal);
						}	
					} else if (varType == MyLiItem.TEXT) {
						if (!MyLiTextValidator.getValidator().isValid(varVal, IMyLiValidator.TEXT)) {
							throw new MyLiValidatorException("this variable value is illegal: not in German numbar format" + varVal);
						}
					}
					
					vars.add(new MyLiVar(varName, varVal, varType));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return vars;
	}
}
