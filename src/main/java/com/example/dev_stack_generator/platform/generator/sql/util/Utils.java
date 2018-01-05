package com.example.dev_stack_generator.platform.generator.sql.util;

import static com.example.dev_stack_generator.platform.generator.sql.Constant.JDBC_TYPE;
import static com.example.dev_stack_generator.platform.generator.sql.Constant.MODE;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.example.dev_stack_generator.platform.generator.sql.model.Mode;
import com.example.dev_stack_generator.platform.generator.sql.model.Parameter;

public class Utils {

	public static String getParameterExpression(Parameter parameter) {
		Assert.notNull(parameter, "parameter must not be null");
		
		String expression = null;
		
		Object value = parameter.getValue();
		if (value != null) {
			expression = value instanceof String ? 
					String.format( "'%s'", value.toString() ) : value.toString();
		} else {
			StringBuilder expressionBuilder = new StringBuilder("#{")
					.append(parameter.getField());
			
			String jdbcType = parameter.getJdbcType();
			if (StringUtils.isNotEmpty(jdbcType)) {
				expressionBuilder.append(", ")
				.append(JDBC_TYPE)
				.append(" = ")
				.append(jdbcType);
			}
			
			Mode mode = parameter.getMode();
			if (mode != null) {
				expressionBuilder.append(", ")
				.append(MODE)
				.append(" = ")
				.append(mode);
			}
			
			expression = expressionBuilder.append("}").toString();
		}
		
		return expression;
	}
	
	public static String getConditionStatement(String condition, String statement) {
		return String.format("<if test=\"%s\">%s</if>", condition, statement);
	}
}
