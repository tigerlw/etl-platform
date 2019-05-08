package com.dap.etl.admin.app.service;

import org.springframework.stereotype.Component;

import com.dap.etl.admin.app.vo.DataTaskVO;
import com.dap.etl.admin.domain.model.task.DataTransTask;

/**
 * 数据任务应用服务层
 * @author liuwei1
 *
 */
@Component
public class DataTaskService {
	
	/**
	 * 创建数据任务
	 * @param taskId
	 * @param taskName
	 * @param originId
	 * @param orgTable
	 * @param objId
	 * @param objTable
	 * @return
	 */
	public DataTaskVO buildDataTask(String taskId, String taskName, String originId, String orgTable, String objId,
			String objTable) 
	{
		DataTransTask dataTask = new DataTransTask();

		dataTask.bulidDataTransTask(taskId, taskName, originId, orgTable, objId, objTable);
		
		DataTaskVO result = new DataTaskVO();
		
		result.setTaskId(dataTask.getTaskId());
		result.setTaskName(dataTask.getTaskName());
		result.setOriginDataSource(dataTask.getOriginDataSource());
		result.setObjDataSource(dataTask.getObjDataSource());

		return result;
	}
	
	/**
	 * 根据taskId 获取任务定义
	 * @param taskId
	 * @return
	 */
	public DataTaskVO getDataTask(String taskId)
	{
		DataTaskVO result = new DataTaskVO();
		DataTransTask dataTask = DataTransTask.getDataTransTask(taskId);
		
		result.setTaskId(dataTask.getTaskId());
		result.setTaskName(dataTask.getTaskName());
		result.setOriginDataSource(dataTask.getOriginDataSource());
		result.setObjDataSource(dataTask.getObjDataSource());

		return result;
		
	}

}
