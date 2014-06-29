package com.tdd;

public interface LimitsValidator {
	void setLimits(int a, int b);
    void validateArgs(int a, int b);
    void validateResult(int a);
}
