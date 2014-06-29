package com.tdd;

public class SubstractOperator extends MathOperator {

	public SubstractOperator() {
		super(1);
		token = "-";
	}

	@Override
	public int resolve(int a, int b, CalculatorProxy calcProxy)
			throws SecurityException, NoSuchMethodException {
		return calcProxy.binaryOperation(calcProxy.getCalculator().getClass()
				.getMethod("substract", int.class, int.class), a, b);
	}

}
