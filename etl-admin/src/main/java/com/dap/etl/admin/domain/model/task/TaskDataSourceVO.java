package com.dap.etl.admin.domain.model.task;

import java.util.List;

import com.dap.etl.admin.domain.model.datasource.ColumnSchemaVO;
import com.dap.etl.admin.domain.model.datasource.DataSource;
import com.dap.etl.admin.domain.model.datasource.MySqlDataSource;

/**
 * 目标数据源
 * @author liuwei1
 *
 */
public class TaskDataSourceVO {
	
	private DataSource dataSource;
	
	private String tableName;
	
	private List<ColumnSchemaVO> columns;
	
	/**
	 * 构建数据任务数据源配置
	 * @param dataSourceId
	 * @param tableName
	 * @return
	 */
	public boolean buildTaskDataSource(String dataSourceId,String tableName)
	{
		this.setTableName(tableName);
		
		this.dataSource = MySqlDataSource.getDataSourceById(dataSourceId);
		
		if(!(dataSource instanceof MySqlDataSource))
		{
			return false;
		}
		
		MySqlDataSource mysql = (MySqlDataSource)dataSource;
		
		this.columns = mysql.getTableSchema(tableName).getColumns();
		return true;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<ColumnSchemaVO> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnSchemaVO> columns) {
		this.columns = columns;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
