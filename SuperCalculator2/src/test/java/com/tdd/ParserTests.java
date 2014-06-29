package com.tdd;

import static org.junit.Assert.*;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
