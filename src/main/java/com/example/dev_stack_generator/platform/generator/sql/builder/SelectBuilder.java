package com.example.dev_stack_generator.platform.generator.sql.builder;

import static com.example.dev_stack_generator.platform.generator.sql.Constant.AND;
import static com.example.dev_stack_generator.platform.generator.sql.Constant.OR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.dev_stack_generator.platform.generator.sql.model.Parameter;

public class SelectBuilder extends AbstractSqlBuilder {

	private List<String> tables					= new ArrayList<>();
	private List<String> columns 				= new ArrayList<>();
	private List<String> conditions				= new ArrayList<>();
	private List<Parameter> parameters 			= new ArrayList<>();
	private List<String> where 					= new ArrayList<>();
	private List<String> whereConditions		= new ArrayList<>();
	private List<String> having 				= new ArrayList<String>();
	private List<String> groupBy 				= new ArrayList<String>();
	private List<String> orderBy				= new ArrayList<String>();
	
	public SelectBuilder select(String column) {
		return select(column, null);
	}
	
	public SelectBuilder select(String... columns) {
		this.columns.addAll(Arrays.asList(columns));
		if (columns != null) {
			for (String column : columns) {
				conditions.add(column);
			}
		}
		return this;
	}
	
	public SelectBuilder select(String column, String condition) {
		columns.add(column);
		conditions.add(condition);
		return this;
	}
	
	public SelectBuilder from(String table) {
		this.tables.add(table);
		return this;
	}
	
	public SelectBuilder from(String... tables) {
		this.tables.addAll(Arrays.asList(tables));
		return this;
	}
	
	public SelectBuilder where(String condition) {
		where.add(condition);
		whereConditions.add( null );
		return this;
	}
	
	public SelectBuilder where(Parameter parameter, String condition) {
		columns.add(parameter.getColumn());
		parameters.add(parameter);
		whereConditions.add(condition);
		return this;
	}
	
	public SelectBuilder and() {
		where.add(AND);
		return this;
	}
	
	public SelectBuilder or() {
		where.add(OR);
		return this;
	}
	
	public SelectBuilder orderBy(String column) {
		orderBy.add( column );
		return this;
	}
	
	public SelectBuilder groupBy(String column) {
		groupBy.add( column );
		return this;
	}
	
	public SelectBuilder having(String condition) {
		having.add( condition );
		return this;
	}
	
	public String build() {
		StringBuilder builder = new StringBuilder();
		sqlClause(builder, "SELECT", columns, "", "", ", ");
		sqlClause(builder, "FROM", tables, "", "", ", ");
		sqlClause(builder, "WHERE", where, null, whereConditions, "(", ")", " AND ");
		sqlClause(builder, "GROUP BY", groupBy, "", "", ", ");
		sqlClause(builder, "HAVING", having, "(", ")", " AND ");
		sqlClause(builder, "ORDER BY", orderBy, "", "", ", ");
		return builder.toString();
	}
}
