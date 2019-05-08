package com.dap.etl.admin.inf.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.dap.etl.admin.app.vo.DataTaskVO;
import com.dap.etl.admin.domain.model.datasource.ColumnSchemaVO;
import com.dap.etl.admin.domain.model.task.TaskDataSourceVO;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SerenityRunner.class)
public class JaxbKettleTransTest {
	
	@Steps
	private TestStep step;
	
	@Test
	public void testBuildSql()
	{
		//given
		step.initial();
		//when
		step.buildSql();
		//then
		step.buildSuccess();
		
	}
	
	
	@Test
	public void testBuildKettleTransFile()
	{
		//given
		step.initailTransTask();
		//when
		step.buildTransTaskFile();
		//then
		step.buildTaskFileSuccess();
	}
	
	
	public static class TestStep
	{
		private TaskDataSourceVO taskDataSourceVO;
		
		private String sql ;
		
		private DataTaskVO dataTaskVO ;
		
		
		@Step("initial DataSource configuration")
		public void initial()
		{
			taskDataSourceVO = new TaskDataSourceVO();
			taskDataSourceVO.setTableName("t_msg");
			
			List<ColumnSchemaVO> columns = new ArrayList<ColumnSchemaVO>();
			
			ColumnSchemaVO column1 = new ColumnSchemaVO();
			column1.setColumnName("msg");
			column1.setColumnType("VARCHAR");
			
			columns.add(column1);
			
			ColumnSchemaVO column2 = new ColumnSchemaVO();
			column2.setColumnName("msgcount");
			column2.setColumnType("INT");
			
			columns.add(column2);
			
			taskDataSourceVO.setColumns(columns);
			
			
		}
		
		@Step("build sql")
		public void buildSql()
		{
			JaxbKettleTrans trans = new JaxbKettleTrans();
			
			sql = trans.buildSql(taskDataSourceVO);
		}
		
		@Step("build sql success")
		public void buildSuccess()
		{
			System.out.println(sql);
			assertNotNull(sql);
			assertEquals("message",sql,"SELECT msg,msgcount from t_msg");
			
		}
		
		
		@Step("initial trans task")
		public void initailTransTask()
		{
			dataTaskVO = new DataTaskVO();
			dataTaskVO.setTaskId("123");
			dataTaskVO.setTaskName("mysqlTomysql");
			
			TaskDataSourceVO orgDataSourceVO = new TaskDataSourceVO();
			orgDataSourceVO.setTableName("t_msg");
			
			List<ColumnSchemaVO> columns = new ArrayList<ColumnSchemaVO>();
			
			ColumnSchemaVO column1 = new ColumnSchemaVO();
			column1.setColumnName("msg");
			column1.setColumnType("VARCHAR");
			
			columns.add(column1);
			
			ColumnSchemaVO column2 = new ColumnSchemaVO();
			column2.setColumnName("msgcount");
			column2.setColumnType("INT");
			
			columns.add(column2);
			
			orgDataSourceVO.setColumns(columns);
			
			
			dataTaskVO.setOriginDataSource(orgDataSourceVO);
			
			
			TaskDataSourceVO objDataSourceVO = new TaskDataSourceVO();
			objDataSourceVO.setTableName("t_msg_cp");
			
			List<ColumnSchemaVO> objcolumns = new ArrayList<ColumnSchemaVO>();
			
			ColumnSchemaVO objcolumn1 = new ColumnSchemaVO();
			objcolumn1.setColumnName("msg");
			objcolumn1.setColumnType("VARCHAR");
			
			objcolumns.add(objcolumn1);
			
			ColumnSchemaVO objcolumn2 = new ColumnSchemaVO();
			objcolumn2.setColumnName("msgcount");
			objcolumn2.setColumnType("INT");
			
			objcolumns.add(column2);
			
			objDataSourceVO.setColumns(objcolumns);
			
			dataTaskVO.setObjDataSource(objDataSourceVO);
			
			
			
		}
		
		
		@Step("build trans task file")
		public void buildTransTaskFile()
		{
			JaxbKettleTrans trans = new JaxbKettleTrans();
			trans.buildKettleTransFile(dataTaskVO);
		}
		
		
		
		@Step("build trans task file success")
		public void buildTaskFileSuccess()
		{
			String target = "mysql_to_mysql.ktr";
		
			String targetPath = JaxbKettleTrans.class.getResource("/").getPath() + target;
			
			File targetFile = new File(targetPath);
			
			boolean result = targetFile.exists();
			
			assertTrue(result);
		}
	}

}
