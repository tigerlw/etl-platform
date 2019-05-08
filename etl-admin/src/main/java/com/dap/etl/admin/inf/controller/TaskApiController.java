package com.dap.etl.admin.inf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dap.etl.admin.app.service.DataTaskService;
import com.dap.etl.admin.app.vo.DataTaskVO;
import com.dap.etl.admin.inf.vo.DataTaskDefinition;

@RestController
public class TaskApiController {
	
	@Autowired
	private DataTaskService dataTaskService;
	
	@RequestMapping(value = "/task/buildtask", method = RequestMethod.POST)
	public DataTaskVO buildDataTask(@RequestBody DataTaskDefinition dataTaskDefinition) 
	{
		DataTaskVO result = dataTaskService.buildDataTask(dataTaskDefinition.getTaskId(), dataTaskDefinition.getTaskName(),
				dataTaskDefinition.getOriginId(), dataTaskDefinition.getOrgTable(), dataTaskDefinition.getObjId(),
				dataTaskDefinition.getObjTable());
		
		
		return result;
	}

}
