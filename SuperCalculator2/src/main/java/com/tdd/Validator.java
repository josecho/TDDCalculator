package com.tdd;

public class Validator implements LimitsValidator {

	private int upperLimit;
	private int lowerLimit;

	public Validator(int lowerLimit, int upperLimit) {
		setLimits(lowerLimit, upperLimit);
	}

	public int getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(int upperLimit) {
		this.upperLimit = upperLimit;
	}

	public int getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(int lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public void validateArgs(int arg1, int arg2) {
		breakIfOverflow(arg1, "First argument exceeds limits");
		breakIfOverflow(arg2, "Second argument exceeds limits");
	}

	public void validateResult(int result) {
		breakIfOverflow(result, "Result exceeds limits");
	}

	private void breakIfOverflow(int arg, String msg) {
		if (valueExceedLimits(arg))
			throw new RuntimeException(msg);
	}

	public boolean valueExceedLimits(int arg) {
		if (arg > upperLimit)
			return true;
		if (arg < lowerLimit)
			return true;
		return false;
	}

	public void setLimits(int lower, int upper) {
		lowerLimit = lower;
		upperLimit = upper;
	}
}
