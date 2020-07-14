package de.jl.myli.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.jl.myli.MyValidator;
import de.jl.myli.items.MyLiItem;
import de.jl.myli.items.MyLiVar;

public class MyLiVarsReader extends MyLiReader{

	public MyLiVarsReader(File file) {
		super(file);
	}
	
	public List<MyLiVar> readFile() {
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
					varVal = line.substring(line.indexOf(0x003d)+1, line.length());
					varType = MyValidator.getVarType(varVal);
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
