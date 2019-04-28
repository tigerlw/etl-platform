package com.dap.etl.admin.domain.model.datasource;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dap.etl.admin.domain.inf.service.MySqlConnection;
import com.dap.etl.admin.inf.connection.MySqlConnectionImpl;

public class MySqlDataSourceTest {
	
	@Test
	public void testConnectionSuccess()
	{
		MySqlConnection connection = new MySqlConnectionImpl();
		
		MySqlDataSource dataSource = new MySqlDataSource("123", "127.0.0.1", "3306", "ocs_test", "root", "123456", connection);
		
		boolean result = dataSource.testConnection();
		
		assertTrue(result);
	}
	
	
	@Test
	public void testConnectionFail()
	{
		MySqlConnection connection = new MySqlConnectionImpl();
		
		MySqlDataSource dataSource = new MySqlDataSource("123", "127.0.0.1", "3306", "ocs_test", "root", "1234567", connection);
		
		boolean result = dataSource.testConnection();
		
		assertFalse(result);
	}
	

}
