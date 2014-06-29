package com.tdd;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CalcProxyTests {
	private BasicCalculator calculator;
	private CalcProxy calcProxy;
	private CalcProxy coordinatorWithLimits;

	@Before
	public void setUp() {
		calculator = new Calculator();
		calcProxy = new CalcProxy(new Validator(-100, 100), calculator);
		coordinatorWithLimits = new CalcProxy(new Validator(-10, 10),
				calculator);
	}

	@Test
	public void add() {

		try {
			int result = calcProxy.binaryOperation(calculator.getClass()
					.getMethod("add", int.class, int.class), 2, 2);
			assertEquals(4, result);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void substract() {
		int result;
		try {
			result = calcProxy.binaryOperation(
					calculator.getClass().getMethod("substract", int.class,
							int.class), 5, 3);
			assertEquals(2, result);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void multiply() {
		try {
			assertEquals(
					calcProxy.binaryOperation(
							calculator.getClass().getMethod("multiply",
									int.class, int.class), 2, 5), 10);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void division() {
		try {
			assertEquals(
					calcProxy.binaryOperation(
							calculator.getClass().getMethod("division",
									int.class, int.class), 10, 2), 5);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void addWithDifferentArguments() {
		try {
			int result = calcProxy.binaryOperation(calculator.getClass()
					.getMethod("add", int.class, int.class), 2, 5);
			assertEquals(7, result);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void substractReturningNegative() {
		try {
			int result = calcProxy.binaryOperation(calculator.getClass()
					.getMethod("substract", int.class, int.class), 3, 5);
			assertEquals(-2, result);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void argumentsExceedLimits() {
		try {
			try {
				coordinatorWithLimits.binaryOperation(calculator.getClass()
						.getMethod("add", int.class, int.class), 30, 50);
				fail("This should fail as arguments exceed both limits");
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (OverflowException oEx) {
			// Ok, this works

		}
	}

	@Test
	public void argumentsExceedLimitsInverse() {
		try {
			try {
				coordinatorWithLimits.binaryOperation(calculator.getClass()
						.getMethod("add", int.class, int.class), -30, -50);
				fail("This should fail as arguments exceed both limits ");
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (OverflowException oEx) {
		}
	}

	@Test
	public void validateResultExceedingUpperLimit() {
		try {
			try {
				coordinatorWithLimits.binaryOperation(calculator.getClass()
						.getMethod("add", int.class, int.class), 10, 10);
				fail("This should fail as arguments exceed both limits ");
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (OverflowException oEx) {
			// Ok , this works
		}
	}

	@Test
	public void validateResultExceedingLowerLimit() {
		try {
			try {
				coordinatorWithLimits.binaryOperation(calculator.getClass()
						.getMethod("add", int.class, int.class), -20, -1);
				fail("This should fail as arguments exceed both limits ");
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (OverflowException oEx) {
			// Ok , this works
		}
	}

	@Test
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// calcProxyMock.VerifyAllExpectations();
	}

}
