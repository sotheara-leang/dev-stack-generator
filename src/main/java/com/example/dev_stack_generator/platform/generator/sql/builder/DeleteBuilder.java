package com.example.dev_stack_generator.platform.generator.sql.builder;

import static com.example.dev_stack_generator.platform.generator.sql.Constant.AND;
import static com.example.dev_stack_generator.platform.generator.sql.Constant.OR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.dev_stack_generator.platform.generator.sql.model.Parameter;

public class DeleteBuilder extends AbstractSqlBuilder {

	private String table;
	private List<Parameter> parameters 	= new ArrayList<>();
	private List<String> where 			= new ArrayList<>();
	private List<String> conditions		= new ArrayList<>();
	
	private DeleteBuilder(String table) {
		this.table = table;
	}
	
	public static DeleteBuilder table(String table) {
		return new DeleteBuilder(table);
	}
	
	public DeleteBuilder set(Parameter parameter) {
		return set(parameter, null);
	}
	
	public DeleteBuilder set(Parameter parameter, String condition) {
		parameters.add(parameter);
		conditions.add(condition);
		return this;
	}
	
	public DeleteBuilder where(String condition) {
		where.add(condition);
		return this;
	}
	
	public DeleteBuilder and() {
		where.add(AND);
		return this;
	}
	
	public DeleteBuilder or() {
		where.add(OR);
		return this;
	}
	
	public String build() {
		StringBuilder builder = new StringBuilder();
		sqlClause(builder, "DELETE FROM", Arrays.asList(table), "", "", "");
	    sqlClause(builder, "WHERE", where, conditions, "(", ")", " AND ");
		return builder.toString();
	}
}
