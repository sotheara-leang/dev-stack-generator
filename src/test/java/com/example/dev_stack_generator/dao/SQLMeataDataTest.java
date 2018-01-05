package com.example.dev_stack_generator.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dev_stack_generator.AbstractServiceTest;

public class SQLMeataDataTest extends AbstractServiceTest {

	@Autowired
	private DataSource dataSource;

	@Test
	public void getTableList() throws SQLException {
		Connection connection = dataSource.getConnection();
		
		ResultSet resutSet = connection.createStatement().executeQuery("show columns from ceceb_ppgtx_l");
		
		while (resutSet.next()) {
			String field 	= resutSet.getString("Field");
			String type 	= resutSet.getString("Type");
			String null_ 	= resutSet.getString("Null");
			String key 		= resutSet.getString("Key");
			String default_	= resutSet.getString("Default");
			
			System.out.println("Field\t\t\t" + field);
			System.out.println("Type\t\t\t" + type);
			System.out.println("Null\t\t\t" + null_);
			System.out.println("Key\t\t\t" + key);
			System.out.println("Default\t\t\t" + default_);
			
			System.out.println();
		}
		
		connection.close();
	}
	
	@Test
	public void getTableColumnList() throws SQLException {
		Connection connection = dataSource.getConnection();
		
		ResultSet resutSet = connection.createStatement().executeQuery("show columns from ceceb_ppgtx_l");
		
		while (resutSet.next()) {
			String field 	= resutSet.getString("Field");
			String type 	= resutSet.getString("Type");
			String null_ 	= resutSet.getString("Null");
			String key 		= resutSet.getString("Key");
			String default_	= resutSet.getString("Default");
			
			System.out.println("Field\t\t\t" + field);
			System.out.println("Type\t\t\t" + type);
			System.out.println("Null\t\t\t" + null_);
			System.out.println("Key\t\t\t" + key);
			System.out.println("Default\t\t\t" + default_);
			
			System.out.println();
		}
		
		connection.close();
	}
}
