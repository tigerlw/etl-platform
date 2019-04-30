package com.dap.etl.admin.domain.model.datasource;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.dap.etl.admin.domain.inf.service.MySqlConnection;
import com.dap.etl.admin.inf.connection.MySqlConnectionImpl;

import net.thucydides.core.annotations.Narrative;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;

/**
 * Mysql 数据源实体测试类
 * @author liuwei1
 *
 */
@RunWith(SerenityRunner.class)
@Narrative(text={"Maths is important."})
public class MySqlDataSourceTest {
	
	@Steps
	private ConnectStep connectStep;
	
	@Test
	@Title("测试数据库链接") 
	public void testConnectionSuccess()
	{
		//Given
		connectStep.initialConnection("127.0.0.1", "3306", "ocs_test", "root", "123456");
		//when
		connectStep.startConnect();
		//then
		connectStep.shouldBe();
	}
	
	
	@Test
	public void testConnectionFail()
	{
		//Given
		connectStep.initialConnection("127.0.0.1", "3306", "ocs_test", "root", "1234567");
		//when
		connectStep.startConnect();
		//then
		connectStep.shouldNotBe();
	}
	
	@Test
    public void testQueryTableList()
    {
    	//Given
    	connectStep.initialConnection("127.0.0.1", "3306", "ocs_test", "root", "123456");
    	//When
    	connectStep.getTables();
    	//Then
    	connectStep.shouldHave();
    }
	
	
	public class ConnectStep
	{
		private MySqlDataSource dataSource;
		
		private boolean result;
		
		private List<TableSchemaVO> tables;
		
		@Step("initial connection ip:{0}, port:{1}, database:{2}, username:{3}, pwd:{4}")
		public void initialConnection(String ip,String port,String database,String userName,String pwd)
		{
			MySqlConnection connection = new MySqlConnectionImpl();
			
			dataSource = new MySqlDataSource("123", ip, port, database, userName, pwd, connection);
		}
		
		@Step("start connect")
		public void startConnect()
		{
			result = dataSource.testConnection();
		}
		
		@Step("get tables")
		public void getTables()
		{
			tables = dataSource.getTableList();
		}
		
		
		@Step("connect success")
		public void shouldBe()
		{
			assertTrue(result);
		}
		
		@Step("connect fail")
		public void shouldNotBe()
		{
			assertFalse(result);
		}
		
		@Step("Get Tables Success")
		public void shouldHave()
		{
			int tablesCount = tables.size();
			
			boolean existColumn = false;

			result = tablesCount > 0 ? true : false;
			
			if(!tables.isEmpty())
			{
			   List<ColumnSchemaVO> column = tables.get(0).getColumns();
			   
			   if(column.size()>0)
			   {
				   existColumn = true;
			   }
			}
			
			
			
			assertTrue(result);
			assertTrue(existColumn);
			
		}
		
		
	}
	

}
