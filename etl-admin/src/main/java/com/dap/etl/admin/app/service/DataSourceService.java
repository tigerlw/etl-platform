package com.dap.etl.admin.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dap.etl.admin.app.vo.DataSourceVO;
import com.dap.etl.admin.domain.inf.service.MySqlConnection;
import com.dap.etl.admin.domain.model.datasource.MySqlDataSource;
/**
 * 
 * @author liuwei1
 *
 */
@Component
public class DataSourceService {
	
	@Autowired
	private MySqlConnection conn;
	
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

}
