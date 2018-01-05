package com.example.dev_stack_generator.generator;

import org.junit.Test;

import com.example.dev_stack_generator.platform.generator.sql.builder.Statement;
import com.example.dev_stack_generator.platform.generator.sql.model.Parameter;

public class SqlGeneratorTest {
	
	@Test
	public void testInsertStmt() {
		String sql = Statement.insert("User")
			.set(Parameter.builder()
					.column("LAST_NAME")
					.field( "lastName" )
					.build())
			.set(Parameter.builder()
					.column("FIRST_NAME")
					.field("firstName")
					.jdbcType("VARCHAR")
					.build())
			.build();
		
		System.out.println(sql);
	}
	
	@Test
	public void testInsertStmtWithCondition() {
		String sql = Statement.insert("User")
			.set(Parameter.builder()
					.column("LAST_NAME")
					.field( "lastName" )
					.build(), "lastName != null")
			.set(Parameter.builder()
					.column("FIRST_NAME")
					.field("firstName")
					.jdbcType("VARCHAR")
					.build())
			.build();
		
		System.out.println(sql);
	}
	
	@Test
	public void testUpdateStmt() {
		String sql = Statement.update("User")
			.set(Parameter.builder()
					.column("LAST_NAME")
					.value( "Leang" )
					.build())
			.set(Parameter.builder()
					.column("FIRST_NAME")
					.field("firstName")
					.jdbcType("VARCHAR")
					.build())
			.build();
		
		System.out.println(sql);
	}
	
	@Test
	public void testUpdateStmtWhere() {
		String sql = Statement.update("User")
			.set(Parameter.builder()
					.column("LAST_NAME")
					.value( "Leang" )
					.build())
			.set(Parameter.builder()
					.column("FIRST_NAME")
					.field( "firstName" )
					.build(), "firstName != null")
			.where("AGE = '22'")
			.where("ADDRESS != null")
			.and()
			.where("SEX = 'F'")
			.build();
		
		System.out.println(sql);
	}
	
	@Test
	public void testSelectStmt() {
		String sql = Statement.select( "LAST_NAME", "FIRST_NAME" )
			.from( "User" )
			.where( "LAST_NAME = 'Leang'" )
			.build();
		
		System.out.println( sql );
	}
}