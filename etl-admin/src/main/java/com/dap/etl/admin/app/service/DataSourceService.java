package com.dap.etl.admin.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dap.etl.admin.app.vo.DataSourceVO;
import com.dap.etl.admin.domain.inf.service.MySqlConnection;
import com.dap.etl.admin.domain.model.datasource.MySqlDataSource;
import com.dap.etl.admin.domain.model.datasource.TableSchemaVO;
/**
 * 
 * @author liuwei1
 *
 */
@Component
public class DataSourceService {
	
	@Autowired
	private MySqlConnection conn;
	
	/**
	 * 测试数据库链接
	 * @param dataSourceVO
	 * @return
	 */
	public String testDataSource(DataSourceVO dataSourceVO)
	{
		
		MySqlDataSource dataSource = new MySqlDataSource(dataSourceVO.getId(), dataSourceVO.getIp(),
				dataSourceVO.getPort(), dataSourceVO.getDataBase(), dataSourceVO.getUserName(), dataSourceVO.getPwd(), conn);
		
		boolean isOk = dataSource.testConnection();
		
		if(isOk)
		{
			return "success";
		}
		
		return "fail";
	}
	
	/**
	 * 获取数据库表元数据
	 * @param dataSourceVO
	 * @return
	 */
	public List<TableSchemaVO> getTableSchema(DataSourceVO dataSourceVO)
	{
		MySqlDataSource dataSource = new MySqlDataSource(dataSourceVO.getId(), dataSourceVO.getIp(),
				dataSourceVO.getPort(), dataSourceVO.getDataBase(), dataSourceVO.getUserName(), dataSourceVO.getPwd(), conn);
		
		List<TableSchemaVO> result = dataSource.getTableList();
		
		return result;
	}

}
