package com.tdd;

import java.lang.reflect.Method;

public class CalcProxy implements CalculatorProxy {

	private BasicCalculator calculator;
	private LimitsValidator validator;

	public CalcProxy(LimitsValidator validator, BasicCalculator calculator) {
		this.validator = validator;
		this.calculator = calculator;
	}

	public BasicCalculator getCalculator() {
		return calculator;
	}

	public void setCalculator(BasicCalculator calculator) {
		this.calculator = calculator;
	}

	public int binaryOperation(Method operation, int arg1, int arg2) {

		validator.validateArgs(arg1, arg2);

		int result = 0;
		Class<?> c;
		
		try {
			c = Class.forName("com.tdd.Calculator");
			Method[] methods = c.getMethods();
			for (Method method : methods) {

				if (method.getName().equals(operation.getName())) {
					Object arglist[] = new Object[2];
					arglist[0] = arg1;
					arglist[1] = arg2;
					result = (Integer) method.invoke(calculator, arglist);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		validator.validateResult(result);
		return result;
	}

}
