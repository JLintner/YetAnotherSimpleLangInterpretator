package de.jl.yasli.validator;

public interface IMyLiValidator {

	public static final Integer NOTSET = -1;
	public static final Integer UKN = 0;
	public static final Integer CONSTANT = 5;
	public static final Integer TEXT = 3;
	public static final Integer VARIABLENAME = 2;

	public static final Integer GERMAN = 8;
	public static final Integer ENGLISH = 9;	
	
	public Boolean isValid(String test, Integer type);
	
}
