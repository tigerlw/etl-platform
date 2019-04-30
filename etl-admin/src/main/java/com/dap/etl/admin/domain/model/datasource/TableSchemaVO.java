package com.dap.etl.admin.domain.model.datasource;

import java.util.List;

/**
 * 数据库表值对象
 * @author liuwei1
 *
 */
public class TableSchemaVO {
	
	private String tableName;
	
	private List<ColumnSchemaVO> columns;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<ColumnSchemaVO> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnSchemaVO> columns) {
		this.columns = columns;
	}

	
}
