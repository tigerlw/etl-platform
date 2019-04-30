package com.dap.etl.admin.domain.inf.service;

import java.util.List;

import com.dap.etl.admin.domain.model.datasource.TableSchemaVO;

/**
 * mysql数据库链接接口服务
 * @author liuwei1
 *
 */
public interface MySqlConnection {
	
	/**
	 * 测试mysql 数据库链接
	 * @param conn
	 * @return
	 */
	public boolean testConnection(MySqlConnVO conn);
	
	/**
	 * 获取数据库表元数据
	 * @param conn
	 * @return
	 */
	public List<TableSchemaVO> getTableList(MySqlConnVO conn);
	
	
	
	

}
