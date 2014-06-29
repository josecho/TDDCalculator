package com.tdd;

public class Calculator implements BasicCalculator {

	public Calculator() {
		super();
	}

	public int add(int arg1, int arg2) {
		int result = arg1 + arg2;
		return result;
	}

	public int substract(int arg1, int arg2) {
		int result = arg1 - arg2;
		return result;
	}

	public int multiply(int arg1, int arg2) {
		return arg1 * arg2;
	}

	public int division(int arg1, int arg2) {
		return arg1 / arg2;
	}
}
