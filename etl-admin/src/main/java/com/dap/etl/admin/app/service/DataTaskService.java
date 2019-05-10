package com.dap.etl.admin.app.service;

import org.springframework.stereotype.Component;

import com.dap.etl.admin.app.vo.DataTaskVO;
import com.dap.etl.admin.domain.model.task.DataTransTask;
import com.dap.etl.admin.domain.model.task.TaskCycleVO.TaskCycleType;

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
		
		DataTaskVO result = transformToVO(dataTask);

		return result;
	}
	
	public DataTaskVO defineTaskCycle(String taskId,long startTime,long endTime,String type,int value)
	{
		DataTransTask dataTask = DataTransTask.getDataTransTask(taskId);
		
		dataTask.defineTaskCycle(startTime, endTime, type, value);
		
		DataTaskVO result = transformToVO(dataTask);
		return result;
	}
	
	/**
	 * 根据taskId 获取任务定义
	 * @param taskId
	 * @return
	 */
	public DataTaskVO getDataTask(String taskId)
	{

		DataTransTask dataTask = DataTransTask.getDataTransTask(taskId);
		
		DataTaskVO result = transformToVO(dataTask);
		
		return result;
		
	}
	
	
	/**
	 * 数据任务实体转换为值对象
	 * @param dataTask
	 * @return
	 */
	private DataTaskVO transformToVO(DataTransTask dataTask)
	{
		DataTaskVO vo = new DataTaskVO();
		vo.setTaskId(dataTask.getTaskId());
		vo.setTaskName(dataTask.getTaskName());
		vo.setOriginDataSource(dataTask.getOriginDataSource());
		vo.setObjDataSource(dataTask.getObjDataSource());
		vo.setTaskCycle(dataTask.getTaskCycle());
		
		return vo;
	}

}
