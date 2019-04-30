package com.dap.etl.admin.inf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dap.etl.admin.app.service.DataSourceService;
import com.dap.etl.admin.app.vo.DataSourceVO;
import com.dap.etl.admin.domain.model.datasource.TableSchemaVO;

@RestController
public class DataSourceApiController 
{
	@Autowired
	private DataSourceService dataSourceService;
	
	@RequestMapping(value = "/datasource/testconnect" ,method = RequestMethod.POST)
	public String testDataSource(@RequestBody DataSourceVO dataSourceVO)
	{
		
		return dataSourceService.testDataSource(dataSourceVO);
	}
	
	@RequestMapping(value = "/datasource/schema" ,method = RequestMethod.POST)
	public List<TableSchemaVO> getDataSourceSchema(@RequestBody DataSourceVO dataSourceVO)
	{
		List<TableSchemaVO> result = dataSourceService.getTableSchema(dataSourceVO);
		return result;
	}
}
