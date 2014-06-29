package com.tdd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class ExpressionFixerTest {
	
	List<MathExpression> expressions;
	MathRegex mathRegex;
	ExpressionFixer fixer;

	@Before
	public void SetUp() {
		expressions = new ArrayList<MathExpression>();
		mathRegex = new MathRegex();
		fixer = new ExpressionFixer(mathRegex);
	}

	@Test
	public void SplitExpressionWhenOperatorAtTheEnd() {
		expressions.add(new MathExpression("2 +"));
		fixer.fixExpressions(expressions);
		assertTrue(expressions.get(0).getExpression().contains("2"));
		assertTrue(expressions.get(1).getExpression().contains("+"));
		/*assertTrue(expressions.get(0).getExpression().equals("2")
				|| expressions.get(0).getExpression().equals("+"));
		assertTrue(expressions.get(1).getExpression().equals("2")
				|| expressions.get(1).getExpression().equals("+"));*/
		
	}
	
	@Test
	public void Trim() {
		expressions.add(new MathExpression(" * "));
		fixer.fixExpressions(expressions);
		assertEquals("*", expressions.get(0).getExpression());
	}
	
}
