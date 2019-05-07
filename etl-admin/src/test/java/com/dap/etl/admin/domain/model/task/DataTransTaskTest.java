package com.dap.etl.admin.domain.model.task;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.dap.etl.admin.domain.inf.service.MySqlConnection;
import com.dap.etl.admin.domain.model.datasource.ColumnSchemaVO;
import com.dap.etl.admin.domain.model.datasource.MySqlDataSource;

import com.dap.etl.admin.inf.connection.MySqlConnectionImpl;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class DataTransTaskTest {
	
	@Steps
	private TaskStep taskStep;
	
	@Test
	public void testBuildTask()
	{
		//given
		taskStep.initailDataSource("127.0.0.1", "3306", "ocs_test", "root", "123456");
		//when
		taskStep.buildTask();
		//then
		taskStep.buildSuccess();
	}
	
	
	public static class TaskStep
	{
		//private MySqlDataSource dataSource;
		
		private DataTransTask task;
		
		@Step("initial dataSource ip:{0}, port:{1}, database:{2}, username:{3}, pwd:{4}")
		public void initailDataSource(String ip,String port,String database,String userName,String pwd)
		{
			MySqlConnection connection = new MySqlConnectionImpl();
	
			MySqlDataSource  orgDataSource = new MySqlDataSource("orgId", ip, port, database, userName, pwd, connection);
			
			orgDataSource.buildDataSource();
			
			MySqlDataSource  objDataSource = new MySqlDataSource("objId", ip, port, database, userName, pwd, connection);
			
			objDataSource.buildDataSource();
			
			
		}
		
		@Step("buildTask")
		public void buildTask()
		{
			task = new DataTransTask();
			
			task.bulidDataTransTask("123", "123", "orgId", "t_msg", "objId", "t_msg");
		}
		
		
		@Step("build Task success")
		public void buildSuccess()
		{
			List<ColumnSchemaVO> orgColumns  = task.getOriginDataSource().getColumns() ;
			List<ColumnSchemaVO> objColumns  = task.getObjDataSource().getColumns() ;
			
			assertNotNull(orgColumns);
			assertNotNull(objColumns);
			
			
		}
	}

}
