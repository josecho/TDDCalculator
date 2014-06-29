package com.tdd;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MathRegexTests {

	MathRegex mathRegex;

	@Before
	public void SetUp() {
		mathRegex = new MathRegex();
	}

	@Test
	public void ValidateMoreThanOneDigitExpression() {
		String expression = "22 + 287";
		boolean result = mathRegex.isExpressionValid(expression);
		assertTrue(result);
	}

	@Test
	public void ValidateSimpleExpressionWithAllOperators() {
		String[] operators = { "+", "-", "*", "/" };
		String expression = "";
		for (String operator : operators) {
			expression = "2 " + operator + " 2";
			assertTrue("Failure with operator: " + operator,
					mathRegex.isExpressionValid(expression));
		}
	}

	@Test
	public void ValidateWithSpaces() {
		String expression = "2     +   287";
		boolean result = mathRegex.isExpressionValid(expression);
		assertTrue(result);
	}

	@Test
	public void ValidateFailsNoSpaces() {
		String expression = "2+287";
		boolean result = mathRegex.isExpressionValid(expression);
		assertFalse(result);
	}

	@Test
	public void ValidateComplexExpresion() {
		String expression = "2 + 7 - 2 * 4";
		boolean result = mathRegex.isExpressionValid(expression);
		assertTrue(result);
	}

	@Test
	public void ValidateComplexWrongExpresion() {
		String expression = "2 + 7 a 2 b 4";
		boolean result = mathRegex.isExpressionValid(expression);
		assertFalse(result);
	}

	@Test
	public void ValidateSimpleWrongExpresion() {
		String expression = "2a4";
		boolean result = mathRegex.isExpressionValid(expression);
		assertFalse(result);
	}

	@Test
	public void ValidateWrongExpresionWithValidSubexpresion() {
		String expression = "2 + 7 - 2 b 4 b";
		boolean result = mathRegex.isExpressionValid(expression);
		assertFalse(result);
	}

	@Test
	public void ValidateWithSeveralOperatorsTogether() {
		String expression = "+ + 7";
		boolean result = mathRegex.isExpressionValid(expression);
		assertFalse(result);
	}

	@Test
	public void ValidateWithNegativeNumbers() {
		assertTrue(mathRegex.isExpressionValid("-7 + 1"));
	}

	@Test
	public void ValidateWithNegativeNumbersAttheEnd() {
		assertTrue(mathRegex.isExpressionValid("7 - -1"));
	}

	@Test
	public void ValidateSuperComplexExpression() {
		assertTrue(mathRegex.isExpressionValid("-7 - -1 * 2 / 3 + -5"));
	}
}
