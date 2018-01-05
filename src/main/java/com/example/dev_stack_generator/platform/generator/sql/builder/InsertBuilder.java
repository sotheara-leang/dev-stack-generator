package com.example.dev_stack_generator.platform.generator.sql.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.dev_stack_generator.platform.generator.sql.model.Parameter;
import com.example.dev_stack_generator.platform.generator.sql.util.Utils;

public class InsertBuilder extends AbstractSqlBuilder {

	private String table;
	private List<String> columns 		= new ArrayList<>();
	private List<String> conditions		= new ArrayList<>();
	private List<Parameter> parameters 	= new ArrayList<>();
	
	private InsertBuilder(String table) {
		this.table = table;
	}
	
	public static InsertBuilder table(String table) {
		return new InsertBuilder(table);
	}
	
	public InsertBuilder set(Parameter parameter) {
		return set(parameter, null);
	}
	
	public InsertBuilder set(Parameter parameter, String condition) {
		columns.add(parameter.getColumn());
		parameters.add(parameter);
		conditions.add(condition);
		return this;
	}
	
	public String build() {
		StringBuilder builder = new StringBuilder();
		sqlClause(builder, "INSERT INTO", Arrays.asList(table), "", "", "");
		sqlClause(builder, "", columns, conditions, "(", ")", "\n , ");
		sqlClause(builder, "VALUES\n", parameters, new ExpressionBuilder<Parameter>() {

			@Override
			public String generateExpression(Parameter parameter) {
				return Utils.getParameterExpression(parameter);
			}
			
		}, conditions, "(", ")", "\n , ");
		return builder.toString();
	}
}
