package com.tdd;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathParser {

	Lexer lexer;
	CalculatorProxy calcProxy;

	public MathParser(Lexer lexer, CalculatorProxy calcProxy) {
		this.lexer = lexer;
		this.calcProxy = calcProxy;
	}

	public int processExpression(String expression) throws SecurityException,
			NoSuchMethodException {

		List<MathExpression> subExpressions = lexer.getExpressions(expression);

		String flatExpression = "";
		for (MathExpression subExp : subExpressions) {
			if (isSubExpression(subExp.getExpression())) {
				flatExpression += resolveSimpleExpression(subExp
						.getExpression());

			} else {
				flatExpression += " " + subExp.getExpression() + " ";
			}
		}
		return resolveSimpleExpression(flatExpression);

	}

	private boolean isSubExpression(String exp) {
		Pattern operatorRegex = Pattern.compile("[\\+|\\-|\\*|/]");
		Matcher matcherOperatorRegex = operatorRegex.matcher(exp);
		Pattern numberRegex = Pattern.compile("\\d+");
		Matcher matcherNumberRegex = numberRegex.matcher(exp);
		if (matcherOperatorRegex.find() && matcherNumberRegex.find()) {
			System.out.println("yes: " + exp);
			return true;
		}
		return false;

	}

	private int resolveSimpleExpression(String expression)
			throws SecurityException, NoSuchMethodException {

		List<MathToken> mathExp = lexer.getTokens(expression);
		while (mathExp.size() > 1) {
			MathOperator op = getMaxPrecedence(mathExp);
			int firstNumber;
			int secondNumber;
			firstNumber = Integer.parseInt(mathExp.get(op.index - 1).token);
			secondNumber = Integer.parseInt(mathExp.get(op.index + 1).token);
			int result = op.resolve(firstNumber, secondNumber, calcProxy);
			replaceTokensWhitResults(mathExp, op.index, result);
		}
		return Integer.parseInt(mathExp.get(0).token);
	}

	private void replaceTokensWhitResults(List<MathToken> tokens,
			int indexOperator, int result) {
		tokens.add(indexOperator - 1, new MathToken(String.valueOf(result)));
		tokens.remove(indexOperator);
		tokens.remove(indexOperator);
		tokens.remove(indexOperator);
	}

	public MathOperator getMaxPrecedence(List<MathToken> tokens) {
		int precedence = 0;
		MathOperator maxPrecedenceOperator = null;
		int index = -1;
		for (MathToken token : tokens) {
			index++;
			if (token.isOperator()) {
				MathOperator op = OperatorFactory.create(token);
				if (op.getPrecedence() >= precedence) {
					precedence = op.getPrecedence();
					maxPrecedenceOperator = op;
					maxPrecedenceOperator.setIndex(index);
				}
			}
		}
		return maxPrecedenceOperator;
	}

}
