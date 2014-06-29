package com.tdd;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MathTokenTests {

	@Test
	public void isOperator() {
		MathToken numberToken = new MathToken("99");
		assertFalse(numberToken.isOperator());
	}

	@Test
	public void isOperatorTrue() {
		MathToken numberToken = new MathToken("*");
		assertTrue(numberToken.isOperator());
	}
}
