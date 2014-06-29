package com.tdd;

public class MathExpression implements Comparable<MathExpression> {

	private String expression;
	private int order;

	public MathExpression(String expression) {
		this.expression = expression;
		order = -1;
	}

	public MathExpression(String expression, int order) {
		this.expression = expression;
		this.order = order;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int compareTo(MathExpression mathexp) {
		return (this.order < mathexp.order ? -1
				: (this.order == mathexp.order ? 0 : 1));
	}

	public boolean isEmpty() {
		return expression.length() == 0;
	}

}
