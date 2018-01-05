package com.example.dev_stack_generator.platform.generator.sql.builder;

import static com.example.dev_stack_generator.platform.generator.sql.Constant.AND;
import static com.example.dev_stack_generator.platform.generator.sql.Constant.OR;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public abstract class AbstractSqlBuilder {

	protected void sqlClause(StringBuilder builder, 
			String keyword, 
			List<String> parts, 
			String open, 
			String close, 
			String conjunction) {
		sqlClause(builder, keyword, parts, null, null, open, close, conjunction);
	}
	
	protected void sqlClause(StringBuilder builder, 
			String keyword, 
			List<String> parts, 
			List<String> conditions, 
			String open, 
			String close, 
			String conjunction) {
		sqlClause(builder, keyword, parts, null, conditions, open, close, conjunction);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> void sqlClause(StringBuilder builder, 
			String keyword, 
			List<T> parts, 
			ExpressionBuilder<T> expressionBuilder, 
			List<String> conditions, 
			String open, 
			String close, 
			String conjunction) {
		
		if (!parts.isEmpty()) {
			if (builder.length() > 0) {
				builder.append("\n");
			}
			builder.append(keyword);
			builder.append(" ");
			builder.append(open);

			String last = "";
			for (int i = 0; i < parts.size(); i++) {
				Object part = parts.get(i);
				
				if (i > 0 && !part.equals(AND) && !part.equals(OR) && !last.equals(AND) && !last.equals(OR)) {
		           builder.append(conjunction);
		        }
				
				String condition = null;
				if (!part.equals(AND) && !part.equals(OR) && !last.equals(AND) && !last.equals(OR)) {
					if (conditions != null && i < conditions.size()) {
						condition = conditions.get(i);
					}
				}
				
				String statement = expressionBuilder != null ? expressionBuilder.generateExpression((T) part) : part.toString();
				if (StringUtils.isNotEmpty(condition)) {
					statement = com.example.dev_stack_generator.platform.generator.sql.util.Utils.getConditionStatement(condition, statement);
				}
				builder.append(statement);
				last = statement;
			}

			builder.append(close);
		}
	}
}
