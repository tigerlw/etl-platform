package com.dap.etl.admin.inf.connection;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dap.etl.admin.EtlApplicationTests;
import com.dap.etl.admin.app.vo.DataSourceVO;
import com.dap.etl.admin.domain.inf.service.MySqlConnection;
import com.dap.etl.admin.domain.model.datasource.ColumnSchemaVO;
import com.dap.etl.admin.domain.model.datasource.MySqlDataSource;
import com.dap.etl.admin.domain.model.datasource.TableSchemaVO;
import com.dap.etl.admin.inf.controller.DataSourceApiController;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;



public class DataSourceApiControllerTest extends EtlApplicationTests{
	
	@Autowired
	private DataSourceApiController dataSourceApiController;
	
	@Steps
	private ConnectStep connectStep;
	
	@Test
	public void testConnection()
	{
		//Given
		connectStep.initialConnection("127.0.0.1", "3306", "ocs_test", "root", "123456");
		//when
		connectStep.startConnect(dataSourceApiController);
		//then
		connectStep.shouldBe();
	}
	
	@Test
    public void testQueryTableList()
	{
		// Given
		connectStep.initialConnection("127.0.0.1", "3306", "ocs_test", "root", "123456");
		// when
		connectStep.getTables(dataSourceApiController);
		// then
		connectStep.shouldHave();
	}
	
	
	public static class ConnectStep
	{
		private DataSourceVO dataSourceVO;
		
		private String result;
		
		private List<TableSchemaVO> tables;
		
		@Step("initial connection ip:{0}, port:{1}, database:{2}, username:{3}, pwd:{4}")
		public void initialConnection(String ip,String port,String database,String userName,String pwd)
		{
			DataSourceVO dataSourceVO = new DataSourceVO();
			dataSourceVO.setId("111");
			dataSourceVO.setIp(ip);
			dataSourceVO.setPort(port);
			dataSourceVO.setDataBase(database);
			dataSourceVO.setUserName(userName);
			dataSourceVO.setPwd(pwd);
			dataSourceVO.setType("mysql");
			
			this.dataSourceVO = dataSourceVO;
		}
		
		@Step("start connect")
		public void startConnect(DataSourceApiController dataSourceApiController)
		{
			result = dataSourceApiController.testDataSource(dataSourceVO);
		}
		
		@Step("get tables")
		public void getTables(DataSourceApiController dataSourceApiController)
		{
			tables = dataSourceApiController.getDataSourceSchema(dataSourceVO);
		}
		
		@Step("connect success")
		public void shouldBe()
		{
			assertEquals("123",result,"success");
		}
		
		@Step("Get Tables Success")
		public void shouldHave()
		{
			int tablesCount = tables.size();
			
			boolean existColumn = false;

			boolean result = tablesCount > 0 ? true : false;
			
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
