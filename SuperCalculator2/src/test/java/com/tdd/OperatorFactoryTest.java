package com.tdd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OperatorFactoryTest {

	@Test
	public void CreateMultiplyOperator() {
		MathOperator op = OperatorFactory.create(new MathToken("*"));
		assertEquals(op.getPrecedence(),2);

	}
	
	@Test
	public void CreateDivisionOperator() {
		MathOperator op = OperatorFactory.create(new MathToken("/"));
		assertEquals(op.getPrecedence(),2);

	}

}
