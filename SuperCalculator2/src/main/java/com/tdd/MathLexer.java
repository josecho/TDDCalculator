package com.tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MathLexer implements Lexer {

	MathRegex mathRegex;
	ExpressionFixer fixer;
	static char OPEN_SUBEXPRESSION = '(';
	static char CLOSE_SUBEXPRESSION = ')';

	public MathLexer(MathRegex mathRegex, ExpressionFixer fixer) {
		super();
		this.mathRegex = mathRegex;
		this.fixer = fixer;

	}

	public List<MathToken> getTokens(String expression) {

		if (expression.length() > 1) {
			if (!mathRegex.isExpressionValid(expression)) {
				throw new InvalidOperationException(expression);
			}
		}
		String[] items = splitExpression(expression);
		return createTokensFromStrings(items);
	}

	private List<MathToken> createTokensFromStrings(String[] items) {
		List<MathToken> tokens = new ArrayList<MathToken>();
		for (String item : items) {
			tokens.add(new MathToken(item));
		}

		return tokens;
	}

	private String[] splitExpression(String expression) {

		return expression.split("\\s+");

	}

	public List<MathExpression> getExpressions(String expression) {
		List<MathExpression> totalExpressionsFound = new ArrayList<MathExpression>();
		int[] openedParenthesis = { 0 };
		int[] startSearchigAt = { 0 };
		getExpressions(expression, startSearchigAt, new MathExpression(""),
				totalExpressionsFound, openedParenthesis);
		if (openedParenthesis[0] != 0) {
			throw new InvalidOperationException("Parenthesis do not match");
		}
		fixer.fixExpressions(totalExpressionsFound);
		Collections.sort(totalExpressionsFound);
		//bubbleSortExpressions(totalExpressionsFound);
		return totalExpressionsFound;
	}

	// / < summary >
	// / Returns the position where the close parenthesis is
	// found or
	// / the position of the last char in the string .
	// / Also populates the list of expressions along the way
	// / </ summary >
	private void getExpressions(String fullInputExpression,
			int[] subExpressionStartIndex,
			MathExpression subExpressionUnderConstruction,
			List<MathExpression> totalSubexpressionsFound,
			int[] openedParanthesis) {

		for (int currentIndex = subExpressionStartIndex[0]; currentIndex < fullInputExpression
				.length(); currentIndex++) {

			char currentChar = fullInputExpression.charAt(currentIndex);
			if (currentChar == OPEN_SUBEXPRESSION) {
				openedParanthesis[0]++;
				subExpressionStartIndex[0] = currentIndex + 1;
				getExpressions(fullInputExpression, subExpressionStartIndex,
						new MathExpression("", subExpressionStartIndex[0]),
						totalSubexpressionsFound, openedParanthesis);
				currentIndex = subExpressionStartIndex[0];
			} else if (currentChar == CLOSE_SUBEXPRESSION) {
				totalSubexpressionsFound.add(subExpressionUnderConstruction);
				subExpressionStartIndex[0] = currentIndex;
				openedParanthesis[0]--;
				return;
			} else {
				// subExpressionUnderConstruction+=String.valueOf(fullInputExpression.charAt(currentIndex));
				String temp = subExpressionUnderConstruction.getExpression();
				temp += String
						.valueOf(fullInputExpression.charAt(currentIndex));
				subExpressionUnderConstruction.setExpression(temp);
				// subExpressionUnderConstruction.setExpression(String.valueOf(fullInputExpression.charAt(currentIndex)));
				if (subExpressionUnderConstruction.getOrder() == -1) {
					subExpressionUnderConstruction.setOrder(currentIndex);
				}
			}
		}
		totalSubexpressionsFound.add(subExpressionUnderConstruction);
		subExpressionStartIndex[0] = subExpressionUnderConstruction
				.getExpression().length();
	}

	/*private void bubbleSortExpressions(List<MathExpression> subExpressions) {
		for (int i = 0; i < subExpressions.size(); i++) {
			for (int j = 0; j < subExpressions.size() - 1; j++) {
				MathExpression exp1 = subExpressions.get(j);
				MathExpression exp2 = subExpressions.get(j + 1);
				if (exp2.getOrder() < exp1.getOrder()) {
					subExpressions.set(j, exp2);
					subExpressions.set(j + 1, exp1);
				}
			}
		}
	}*/

}
