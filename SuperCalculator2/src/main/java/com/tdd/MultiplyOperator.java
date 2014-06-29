package com.tdd;

public class MultiplyOperator extends MathOperator {

	public MultiplyOperator() {
		super(2);
		token = "*";
	}

	@Override
	public int resolve(int a, int b, CalculatorProxy calcProxy)
			throws SecurityException, NoSuchMethodException {
		return calcProxy.binaryOperation(calcProxy.getCalculator().getClass()
				.getMethod("multiply", int.class, int.class), a, b);
	}

}
