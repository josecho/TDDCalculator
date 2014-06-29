package com.tdd;

import java.util.List;

public class ExpressionFixer {

	MathRegex mathRegex;

	public ExpressionFixer(MathRegex mathRegex) {
		super();
		this.mathRegex = mathRegex;
	}

	public void fixExpressions(List<MathExpression> expressions) {
		boolean listHasChanged = true;
		while (listHasChanged) {
			listHasChanged = false;
			for (int i = 0; i < expressions.size(); i++) {

				if (mathRegex.isNumberAndOperator(expressions.get(i)
						.getExpression())) {
					splitByOperator(expressions, expressions.get(i)
							.getExpression(), i);
					listHasChanged = true;
					break;
				}
				if (expressions.get(i).getExpression().length() == 0) {
					expressions.remove(i);
					listHasChanged = true;
					break;
				}
			}
		}
	}

	private void splitByOperator(List<MathExpression> expressions,
			String inputExpression, int position) {
		String[] nexExps = inputExpression.split("\\s+", 2);
		int order = expressions.get(position).getOrder();
		expressions.remove(position);
		for (String subExpr : nexExps) {
			expressions
					.add(position, new MathExpression(subExpr.trim(), order));
			position++;
		}
	}

}
