package com.tdd;

//public abstract class MathToken
public class MathToken {

	protected int precedence = 0;
	protected String token;
	protected int index = -1;
	protected MathToken previousToken, nextToken;

	public MathToken(String token) {
		this.token = token;
	}

	public MathToken(int precedence) {
		this.precedence = precedence;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public MathToken getPreviousToken() {
		return previousToken;
	}

	public void setPreviousToken(MathToken previousToken) {
		this.previousToken = previousToken;
	}

	public MathToken getNextToken() {
		return nextToken;
	}

	public void setNextToken(MathToken nextToken) {
		this.nextToken = nextToken;
	}

	public int getPrecedence() {
		return precedence;
	}

	public String getToken() {
		return token;
	}

	public boolean isOperator() {
		String operators = "+-*/";
		for (int i = 0; i < operators.length(); i++) {
			if (token.equals(String.valueOf(operators.charAt(i)))) {
				return true;
			}
		}
		return false;
	}
}
