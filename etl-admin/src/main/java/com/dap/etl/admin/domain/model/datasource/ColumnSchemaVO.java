package com.dap.etl.admin.domain.model.datasource;

/**
 * 数据库表列值对象
 * @author liuwei1
 *
 */
public class ColumnSchemaVO {
	
	private String columnName;
	
	private String columnType;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	
	

}
