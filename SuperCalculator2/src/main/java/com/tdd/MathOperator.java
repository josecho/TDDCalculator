package com.tdd;

public abstract class MathOperator {

	int precedence = 0;
	String token = "";
	int index = -1;

	public MathOperator(int precedence) {
		super();
		this.precedence = precedence;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getPrecedence() {
		return precedence;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public abstract int resolve(int a, int b, CalculatorProxy calcProxy)
			throws SecurityException, NoSuchMethodException;

}
