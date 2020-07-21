package de.jl.yasli.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyLiReader {

	protected File f = null;
	
	public MyLiReader(File file) {
		this.f = file;
	}
	
	public String fileToString() {
		String s = "";
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			try {
				while((line = br.readLine()) != null) {
					s = s.concat(line).concat(System.lineSeparator());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	

}
