package com.tdd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathRegex {
	
	public boolean isExpressionValid(String expression) {
		Pattern p = Pattern.compile("^-{0,1}\\d+((\\s+)[+\\-/*](\\s+)-{0,1}\\d+)+$");
		Matcher m = p.matcher(expression);
		return m.find();
	}

	public boolean isNumberAndOperator(String expression) {
		
		Pattern startsWithOperator = Pattern.compile("^(\\s*)[+|\\-|/|*](\\s+)");
		Matcher matcherStartWithOperator = startsWithOperator.matcher(expression);
		Pattern endsWithOperator = Pattern.compile("(\\s+)[+|\\-|/|*](\\s*)$");
		Matcher matcherEndWithOperator = endsWithOperator.matcher(expression);
		if (matcherStartWithOperator.find() || matcherEndWithOperator.find()){
			return true;
		}
		return false;
		
		
	}

}
