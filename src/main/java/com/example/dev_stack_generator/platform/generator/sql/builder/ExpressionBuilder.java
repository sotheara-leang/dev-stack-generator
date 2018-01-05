package com.example.dev_stack_generator.platform.generator.sql.builder;

public interface ExpressionBuilder<T> {

	String generateExpression(T part);
}
