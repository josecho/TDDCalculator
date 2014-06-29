package com.tdd;

public class AddOperator extends MathOperator {

	public AddOperator() {
		super(1);
		token = "+";
	}

	@Override
	public int resolve(int a, int b, CalculatorProxy calcProxy)
			throws SecurityException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return calcProxy.binaryOperation(calcProxy.getCalculator().getClass()
				.getMethod("add", int.class, int.class), a, b);
	}

}
