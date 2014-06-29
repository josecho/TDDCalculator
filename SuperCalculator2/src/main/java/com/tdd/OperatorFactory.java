package com.tdd;

public class OperatorFactory {

	public static MathOperator create(MathToken token) {
		if (token.getToken().equals("*")) {
			return new MultiplyOperator();
		} else if (token.getToken().equals("/")) {
			return new DivideOperator();
		} else if (token.getToken().equals("+")) {
			return new AddOperator();
		} else if (token.getToken().equals("-")) {
			return new SubstractOperator();
		} else {
			throw new InvalidOperationException(
					"The given token is not a valid operator");
		}
	}
}
