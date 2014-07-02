package com.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


public class LexerTests {

	MathLexer lexer;
	MathRegex mathRegex;
	ExpressionFixer fixer;

	@Before
	public void SetUp() {
		mathRegex = new MathRegex();
		fixer = new ExpressionFixer(mathRegex);
		lexer = new MathLexer(mathRegex, fixer);

	}

	@Test
	public void GetTokens() {
		List<MathToken> tokens = lexer.getTokens("2 + 2");
		assertEquals(3, tokens.size());
		assertEquals("2", tokens.get(0).getToken());
		assertEquals("+", tokens.get(1).getToken());
		assertEquals("2", tokens.get(2).getToken());
	}

	@Test
	public void GetTokensLongExpression() {
		List<MathToken> tokens = lexer.getTokens("2 - 1 + 3");
		assertEquals(5, tokens.size());
		assertEquals("+", tokens.get(3).getToken());
		assertEquals("3", tokens.get(4).getToken());
	}

	@Test
	public void GetTokensWithSpaces() {
		List<MathToken> tokens = lexer.getTokens("5 -   8");
		assertEquals("5", tokens.get(0).getToken());
		assertEquals("-", tokens.get(1).getToken());
		assertEquals("8", tokens.get(2).getToken());
	}

	@Test
	public void GetsTokensWrongExpression() {
		try {
			List<MathToken> tokens = lexer.getTokens("2 - 1++ 3");
			fail("Exception did not arise !");
		} catch (InvalidOperationException ioEx) {
			// IllegalStateException:
			// TODO: handle exception
		}
	}

	
	@Test
	public void getExpressionWiht1Parenthesis() {
		List<MathExpression> expression = lexer.getExpressions("(2 + 2)");
		assertEquals(1, expression.size());
		assertEquals("2 + 2", expression.get(0).getExpression());
	}

	@Test
	public void getExpressionWihtNestedParenthesis() {
		List<MathExpression> expressions = lexer.getExpressions("((2) + 2)");
		failIfOtherSubExpressionThan(expressions, "2", "+");
	}

	@Test
	public void GetNestedExpressions() {
		List<MathExpression> expressions = lexer.getExpressions("((2 + 1) + 2)");
		assertEquals(3, expressions.size());
		failIfOtherSubExpressionThan(expressions, "+", "2", "2 + 1");
	}

	@Test
	public void GetExpressionsWithParenthesistTheEnd() {
		List<MathExpression> expressions = lexer.getExpressions("2 + (3 * 1)");
		assertEquals(3, expressions.size());
		failIfOtherSubExpressionThan(expressions, "3 * 1", "+", "2");
	}

	@Test
	public void GetExpressionsWithTwoGroups() {
		List<MathExpression> expressions = lexer.getExpressions("(2 + 2) * (3 + 1)");
		failIfOtherSubExpressionThan(expressions, "3 + 1", "2 + 2", "*");
	}

	
	
	private void failIfOtherSubExpressionThan(List<MathExpression> expressions,
			String... expectedSubExpressions) {
		boolean isSubExpression = false;
		for(String subExpression : expectedSubExpressions){
			isSubExpression= false;
			for(MathExpression exp : expressions){
				if(exp.getExpression().equals(subExpression)){
					isSubExpression = true;
					break;
				}
			}
			if(!isSubExpression){
				fail("Wrong expression split: " + subExpression);
			}
		}
	}
	
	@Test
	public void ThrowExceptionOnOpenParenthesis() {
		try {
			List<MathExpression> expressions = lexer.getExpressions("(2 + 3 * 1");
			fail("Exception did not arise !");
		} catch (InvalidOperationException ioEx) {
			// TODO: handle exception
		}
	}

	@Test
	public void GetSeveralParenthesisExpressionsInOrder() {
		List<MathExpression> expressions = lexer.getExpressions("(2 + 2) * (3 + 1)");
		for (MathExpression exp : expressions) {
			System.out.println(" x : " + exp.getExpression() + " . ");
			assertEquals("2 + 2", expressions.get(0).getExpression());
			assertEquals("*", expressions.get(1).getExpression());
			assertEquals("3 + 1", expressions.get(2).getExpression());
		}
	}

}
