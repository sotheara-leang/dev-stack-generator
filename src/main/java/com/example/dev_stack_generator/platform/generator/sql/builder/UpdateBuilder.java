package com.example.dev_stack_generator.platform.generator.sql.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.dev_stack_generator.platform.generator.sql.Constant.*;
import com.example.dev_stack_generator.platform.generator.sql.model.Parameter;
import com.example.dev_stack_generator.platform.generator.sql.util.Utils;

public class UpdateBuilder extends AbstractSqlBuilder {

	private String table;
	private List<String> columns 		= new ArrayList<>();
	private List<String> conditions		= new ArrayList<>();
	private List<Parameter> parameters 	= new ArrayList<>();
	private List<String> where 		= new ArrayList<>();
	
	private UpdateBuilder(String table) {
		this.table = table;
	}
	
	public static UpdateBuilder table(String table) {
		return new UpdateBuilder(table);
	}
	
	public UpdateBuilder set(Parameter parameter) {
		return set(parameter, null);
	}
	
	public UpdateBuilder set(Parameter parameter, String condition) {
		columns.add(parameter.getColumn());
		parameters.add(parameter);
		conditions.add(condition);
		return this;
	}
	
	public UpdateBuilder where(String condition) {
		where.add(condition);
		return this;
	}
	
	public UpdateBuilder and() {
		where.add(AND);
		return this;
	}
	
	public UpdateBuilder or() {
		where.add(OR);
		return this;
	}
	
	public String build() {
		StringBuilder builder = new StringBuilder();
		sqlClause(builder, "UPDATE", Arrays.asList(table), null, "", "", "");
			
		sqlClause(builder, "SET\n", parameters, new ExpressionBuilder<Parameter>() {

			@Override
			public String generateExpression(Parameter parameter) {
				return parameter.getColumn() + " = " + Utils.getParameterExpression(parameter);
			}
			
		}, conditions, "", "", "\n , ");
		sqlClause(builder, "WHERE", where, conditions, "(", ")", " AND ");
		
		return builder.toString();
	}
}
