package com.tdd;

import java.lang.reflect.Method;

public interface CalculatorProxy {
	
	int binaryOperation(Method operation, int arg1, int arg2);
    BasicCalculator getCalculator();
    void setCalculator(BasicCalculator calculator);

}
