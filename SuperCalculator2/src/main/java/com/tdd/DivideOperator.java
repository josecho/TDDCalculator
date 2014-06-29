package com.tdd;

public class DivideOperator extends MathOperator {

	public DivideOperator() {
		super(2);
		token = "/";
	}

	@Override
	public int resolve(int a, int b, CalculatorProxy calcProxy)
			throws SecurityException, NoSuchMethodException {
		return calcProxy.binaryOperation(calcProxy.getCalculator().getClass()
				.getMethod("division", int.class, int.class), a, b);
	}

}
