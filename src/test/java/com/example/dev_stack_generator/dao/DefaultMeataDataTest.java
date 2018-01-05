package com.example.dev_stack_generator.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dev_stack_generator.AbstractServiceTest;

public class DefaultMeataDataTest extends AbstractServiceTest {

	@Autowired
	private DataSource dataSource;

	@Test
	public void getTablesList() throws SQLException {
		Connection connection = dataSource.getConnection();
		DatabaseMetaData metaData = connection.getMetaData();

		ResultSet tables = metaData.getTables(null, null, null, Arrays.array("TABLE"));

		while (tables.next()) {
			String TABLE_NAME 		= getProperty(tables, "TABLE_NAME");
			String TABLE_TYPE 		= getProperty(tables, "TABLE_TYPE");
			String REMARKS 	  		= getProperty(tables, "REMARKS");
			String TYPE_CAT  	  	= getProperty(tables, "TYPE_CAT");
			String TYPE_SCHEM  	  	= getProperty(tables, "TYPE_SCHEM");
			String TYPE_NAME  	  	= getProperty(tables, "TYPE_NAME");
			
			String SELF_REFERENCING_COL_NAME   	= getProperty(tables, "SELF_REFERENCING_COL_NAME");
			String REF_GENERATION   	  		= getProperty(tables, "REF_GENERATION");
			
			System.out.println("TABLE_NAME\t\t\t" + TABLE_NAME);
			System.out.println("TABLE_TYPE\t\t\t" + TABLE_TYPE);
			System.out.println("REMARKS\t\t\t" + REMARKS);
			System.out.println("TYPE_CAT\t\t\t" + TYPE_CAT);
			System.out.println("TYPE_SCHEM\t\t\t" + TYPE_SCHEM);
			
			System.out.println("TYPE_NAME\t\t\t" + TYPE_NAME);
			System.out.println("SELF_REFERENCING_COL_NAME\t" + SELF_REFERENCING_COL_NAME);
			System.out.println("REF_GENERATION\t\t\t" + REF_GENERATION);

			System.out.println();
		}
		
		connection.close();
	}

	@Test
	public void getTableColumnList() throws SQLException {
		Connection connection = dataSource.getConnection();
		
		DatabaseMetaData metaData = connection.getMetaData();

		ResultSet columns = metaData.getColumns(null, null, "ceceb_ppgtx_l", null);
		while (columns.next()) {
			String COLUMN_NAME 		= getProperty(columns, "COLUMN_NAME");
			String TYPE_NAME 		= getProperty(columns, "TYPE_NAME");

			Integer COLUMN_SIZE 	= getIntProperty(columns, "COLUMN_SIZE");
			String DECIMAL_DIGITS	= getProperty(columns, "DECIMAL_DIGITS");
			String IS_NULLABLE 		= getProperty(columns, "IS_NULLABLE");
			String NUM_PREC_RADIX 	= getProperty(columns, "NUM_PREC_RADIX");
			String COLUMN_DEF 		= getProperty(columns, "COLUMN_DEF");
			String IS_AUTOINCREMENT = getProperty(columns, "IS_AUTOINCREMENT");
			String DATA_TYPE 		= getProperty(columns, "DATA_TYPE");
			String REMARK 			= getProperty(columns, "BUFFER_LENGTH ");
			
			String SQL_DATA_TYPE 	= getProperty(columns, "SQL_DATA_TYPE ");

			System.out.println("Name\t\t\t" + COLUMN_NAME);
			System.out.println("Type\t\t\t" + TYPE_NAME);
			System.out.println("Size\t\t\t" + COLUMN_SIZE);
			System.out.println("Decimal Digits\t\t" + DECIMAL_DIGITS);
			System.out.println("Is Nullable\t\t" + IS_NULLABLE);
			System.out.println("Num Prec Radix\t\t" + NUM_PREC_RADIX);
			System.out.println("Column Def\t\t" + COLUMN_DEF);
			System.out.println("Is Autoincrement\t" + IS_AUTOINCREMENT);
			System.out.println("DATA_TYPE\t\t" + DATA_TYPE);
			System.out.println("Remark\t\t\t" + REMARK);
			System.out.println("SQL_DATA_TYPE\t\t\t" + SQL_DATA_TYPE);
			
			System.out.println();
		}
		
		connection.close();
	}
	
	@Test
	public void getTableColumnList2() throws SQLException {
		Connection connection = dataSource.getConnection();
		String catalog = connection.getCatalog();
		
		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet attributes = metaData.getAttributes(catalog, null, null, null);
		
		while (attributes.next()) {
			attributes.getMetaData();
		}
	}
	
	private String getProperty(ResultSet resultSet, String propertyName) {
		try {
			return resultSet.getString(propertyName);
		} catch (SQLException e) {
			return "NA";
		}
	}
	
	private int getIntProperty(ResultSet resultSet, String propertyName) {
		try {
			return resultSet.getInt(propertyName);
		} catch (SQLException e) {
			return -1;
		}
	}
}
