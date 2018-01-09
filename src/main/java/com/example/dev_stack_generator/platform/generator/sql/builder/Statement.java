package com.example.dev_stack_generator.platform.generator.sql.builder;

public class Statement {

	public static SelectBuilder select(String... columns) {
		SelectBuilder selectBuilder = new SelectBuilder();
		selectBuilder.select( columns );
		return selectBuilder;
	}
	
	public static InsertBuilder insert(String table) {
		return InsertBuilder.table( table );
	}
	
	public static UpdateBuilder update(String table) {
		return UpdateBuilder.table( table );
	}
	
	public static DeleteBuilder delete(String table) {
		return DeleteBuilder.table( table );
	}
}
