package com.tdd;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ParserTests {
	MathRegex mathRegex;// pendente eliminar
	Validator validator;
	Calculator calculator;
	MathLexer lexer;
	MathParser parser;
	CalcProxy calcProxy;
	ExpressionFixer fixer;

	@Before
	public void setUp() {
		mathRegex = new MathRegex();
		calculator = new Calculator();
		validator = new Validator(-100, 100);
		calcProxy = new CalcProxy(validator, calculator);
		fixer = new ExpressionFixer(mathRegex);
		lexer = new MathLexer(mathRegex, fixer);
		parser = new MathParser(lexer, calcProxy);
	}

	@Test
	public void processSimpleExpression() {
		try {
			assertEquals(4, parser.processExpression("2 + 2"));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void ParserWorksWithLexer() {
	List<MathToken> tokens = new ArrayList<MathToken>();
	tokens.add(new MathToken("2"));
	tokens.add(new MathToken("+"));
	tokens.add(new MathToken("2"));
	Lexer lexerMock = mock(Lexer.class);
	when(lexerMock.getTokens("2 + 2")).thenReturn(tokens);
	MathParser parser = new MathParser(lexerMock, new CalcProxy(
	new Validator(-100, 100), new Calculator()));
	try {
	parser.processExpression("2 + 2");
	} catch (SecurityException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} catch (NoSuchMethodException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	verify(lexerMock).getTokens("2 + 2");
	assertEquals(tokens, lexerMock.getTokens("2 + 2"));
	}*/
	
	/*@Test
	public void parserWorksWithCalcProxy() {
		CalculatorProxy calcProxyMock = mock(CalculatorProxy.class);

		// para que funcione
		Lexer lexerMock = mock(Lexer.class);
		List<MathToken> tokens = new ArrayList<MathToken>();
		tokens.add(new MathToken("2"));
		tokens.add(new MathToken("+"));
		tokens.add(new MathToken("2"));
		// //////

		try {
			when(calcProxyMock.getCalculator()).thenReturn(calculator);
			// System.out.println(calcProxyMock.getCalculator());
			when(
					calcProxyMock.binaryOperation(calculator.getClass()
							.getMethod("add", int.class, int.class), 2, 2))
					.thenReturn(4);
			// para que funcione
			when(lexerMock.getTokens("2 + 2")).thenReturn(tokens);

			MathParser parser = new MathParser(lexerMock, calcProxyMock);

			parser.processExpression("2 + 2");

			verify(calcProxyMock).binaryOperation(
					calculator.getClass()
							.getMethod("add", int.class, int.class), 2, 2);
			verify(calcProxyMock).getCalculator();
			// A revisar
			assertEquals(calculator, calcProxyMock.getCalculator());
			assertEquals(4, calcProxyMock.binaryOperation(calculator.getClass()
					.getMethod("add", int.class, int.class), 2, 2));

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		// calcProxyMock.VerifyAllExpectations();
	}*/
	
	
	
	
	

	@Test
	public void processExpression2Operators() {
		try {
			assertEquals(6, parser.processExpression("3 + 1 + 2"));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void processExpressionWithPrecedence() {
		try {
			assertEquals(9, parser.processExpression("3 + 3 * 2"));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getMaxPrecedence() {
		List<MathToken> tokens = lexer.getTokens("3 + 3 * 2");
		MathOperator op = parser.getMaxPrecedence(tokens);
		assertEquals(op.getToken(), "*");
	}

	@Test
	public void processAceptanceExpression() {
		try {
			assertEquals(9, parser.processExpression("5 + 4 * 2 / 2"));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void processAceptanceExpressionWithAllOperators() {
		try {
			assertEquals(8, parser.processExpression("5 + 4 - 1 * 2 / 2"));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void processAceptanceExpressionWithParenthesis() {
		try {
			assertEquals(16, parser.processExpression("(2 + 2) * (3 + 1)"));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
