package com.tdd;

import java.util.List;

public interface Lexer {
	List<MathToken> getTokens(String expression);
    List<MathExpression> getExpressions(String expression);
}
