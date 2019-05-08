package com.dap.etl.admin.inf.controller;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dap.etl.admin.EtlApplicationTests;
import com.dap.etl.admin.app.vo.DataSourceVO;
import com.dap.etl.admin.app.vo.DataTaskVO;

import com.dap.etl.admin.domain.model.datasource.ColumnSchemaVO;

import com.dap.etl.admin.inf.vo.DataTaskDefinition;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class TaskApiControllerTest extends EtlApplicationTests{
	
	@Autowired
	private DataSourceApiController dataSourceApiController;
	
	@Autowired
	private TaskApiController taskApiController;
	
	@Steps
	private TaskStep taskStep;
	
	@Test
	public void testBuildTask()
	{
		// given
		taskStep.initailDataSource("127.0.0.1", "3306", "ocs_test", "root", "123456",dataSourceApiController);
		// when
		taskStep.buildTask(taskApiController);
		// then
		taskStep.buildSuccess();
	}
	
	public static class TaskStep
	{
		private DataTaskVO dataTaskVO;
		
		@Step("initial dataSource ip:{0}, port:{1}, database:{2}, username:{3}, pwd:{4}")
		public void initailDataSource(String ip, String port, String database, String userName, String pwd,
				DataSourceApiController dataSourceApiController) {
			
			//this.dataSourceApiController = dataSourceApiController;
			
			DataSourceVO orgDataSourceVO = new DataSourceVO();
			orgDataSourceVO.setId("orgId");
			orgDataSourceVO.setIp(ip);
			orgDataSourceVO.setPort(port);
			orgDataSourceVO.setDataBase(database);
			orgDataSourceVO.setUserName(userName);
			orgDataSourceVO.setPwd(pwd);
			orgDataSourceVO.setType("mysql");
			
			dataSourceApiController.buildDataSource(orgDataSourceVO);
			
			DataSourceVO objDataSourceVO = new DataSourceVO();
			objDataSourceVO.setId("objId");
			objDataSourceVO.setIp(ip);
			objDataSourceVO.setPort(port);
			objDataSourceVO.setDataBase(database);
			objDataSourceVO.setUserName(userName);
			objDataSourceVO.setPwd(pwd);
			objDataSourceVO.setType("mysql");
			
			dataSourceApiController.buildDataSource(objDataSourceVO);
			

		}
		
		@Step("buildTask")
		public void buildTask(TaskApiController taskApiController)
		{
			
			
			DataTaskDefinition dataTaskDefinition =new DataTaskDefinition();
			
			dataTaskDefinition.setTaskId("123");
			dataTaskDefinition.setTaskName("123");
			dataTaskDefinition.setOriginId("orgId");
			dataTaskDefinition.setOrgTable("t_msg");
			dataTaskDefinition.setObjId("objId");
			dataTaskDefinition.setObjTable("t_msg");
			
			dataTaskVO = taskApiController.buildDataTask(dataTaskDefinition);
		}
		
		
		@Step("build Task success")
		public void buildSuccess()
		{
			List<ColumnSchemaVO> orgColumns  = dataTaskVO.getOriginDataSource().getColumns() ;
			List<ColumnSchemaVO> objColumns  = dataTaskVO.getObjDataSource().getColumns() ;
			
			assertNotNull(orgColumns);
			assertNotNull(objColumns);
			
			
		}
	}

}
