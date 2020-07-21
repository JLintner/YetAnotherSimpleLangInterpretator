package de.jl.yasli.utils;

import java.text.NumberFormat;
import java.util.Locale;

import de.jl.yasli.validator.IMyLiValidator;
import de.jl.yasli.validator.MyLiNumberValidator;

public class MyLiUtils {

	public static Double convertToDouble(String value) throws MyLiValidatorException {
		String tmp = value;
		if (MyLiNumberValidator.getValidator().validateEnglishFormat(value)) {
			while(value.indexOf(UniCodeChars.COMMA)>0) {
				tmp = value.substring(0, value.indexOf(UniCodeChars.COMMA));
				tmp = tmp.concat(value.substring(value.indexOf(UniCodeChars.COMMA)+1, value.length()));
				value = tmp;
			}			
		} else if (MyLiNumberValidator.getValidator().validateGermanFormat(value)) {
			while(tmp.indexOf(UniCodeChars.FULLSTOP)>0) {
				tmp = value.substring(0, value.indexOf(UniCodeChars.FULLSTOP));
				tmp = tmp.concat(value.substring(value.indexOf(UniCodeChars.FULLSTOP)+1, value.length()));
				value = tmp;
			}			
			while(tmp.indexOf(UniCodeChars.COMMA)>0) {
				tmp = tmp.replace(UniCodeChars.getChar(UniCodeChars.COMMA), UniCodeChars.getChar(UniCodeChars.FULLSTOP));
				value = tmp;
			}
		} else if (MyLiNumberValidator.getValidator().validateNumber(value)) {
			tmp = value;
		} else {
			throw new MyLiValidatorException("The number format is unkown. ["+value+"]");
		}
		return Double.valueOf(tmp);
	}
	
	public static String converNumberformat(Double number, Integer targetFormat) {


		Integer format = MyLiNumberValidator.getValidator().getNumberFormat(String.valueOf(number.doubleValue()));

		if (format==targetFormat)
			return String.valueOf(number.doubleValue());
		NumberFormat nf = null;
		if (targetFormat == IMyLiValidator.GERMAN) {
			nf = NumberFormat.getInstance(new Locale("de", "DE"));
		} else if (targetFormat == IMyLiValidator.ENGLISH) {
			nf = NumberFormat.getInstance(new Locale("en", "EN"));
		} else {
			return String.valueOf(number.doubleValue());
		}
		return nf.format(number);
	}
	public static String trimmMarks(String value) {
		if (value.indexOf(UniCodeChars.QUOTATIONMARK) == 0) {
			value = value.substring(1);
		}
		if (value.indexOf(UniCodeChars.QUOTATIONMARK) == value.length()-1) {
			value = value.substring(0, value.length()-1);
		}		
		if (value.indexOf(UniCodeChars.APOSTROPHE) == 0) {
			value = value.substring(1);
		}
		if (value.indexOf(UniCodeChars.APOSTROPHE) == value.length()-1) {
			value = value.substring(0, value.length()-1);
		}
		return value;
	}
}
