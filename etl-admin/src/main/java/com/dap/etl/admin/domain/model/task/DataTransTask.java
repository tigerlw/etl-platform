package com.dap.etl.admin.domain.model.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dap.etl.admin.domain.model.task.TaskCycleVO.TaskCycleType;

/**
 * 数据任务实体
 * @author liuwei1
 *
 */
public class DataTransTask {
	
	private String taskId;
	
	private String taskName;
	
	//源数据源
	private TaskDataSourceVO OriginDataSource;
	
	//目标数据源
	private TaskDataSourceVO ObjDataSource;
	
	//任务周期
	private TaskCycleVO taskCycle;
	
	private static Map<String,DataTransTask> taskMapStore = new ConcurrentHashMap<String,DataTransTask>(128);
	
	/**
	 * 构建数据任务
	 * @param taskId
	 * @param taskName
	 * @param originId
	 * @param orgTable
	 * @param objId
	 * @param objTable
	 * @return
	 */
	public boolean bulidDataTransTask(String taskId, String taskName, String originId,String orgTable, String objId,String objTable) 
	{
		this.taskId = taskId;
		this.taskName = taskName;
		
		this.OriginDataSource = new TaskDataSourceVO();
		this.OriginDataSource.buildTaskDataSource(originId, orgTable);
		this.ObjDataSource = new TaskDataSourceVO();
		this.ObjDataSource.buildTaskDataSource(objId, objTable);
		
		taskMapStore.put(taskId, this);
		
		return true;
	}
	
	/**
	 * 定义任务周期
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param value
	 * @return
	 */
	public boolean defineTaskCycle(long startTime,long endTime,String type,int value)
	{	
		TaskCycleVO cycle = new TaskCycleVO(startTime,endTime,type,value);
		this.taskCycle = cycle;
		return true;
	}
	
	public static DataTransTask getDataTransTask(String taskId)
	{
		return taskMapStore.get(taskId);
	}
	
	
	/**
	 * 查询数据任务
	 * @param taskId
	 * @return
	 */
	public static DataTransTask queryTask(String taskId)
	{
		return taskMapStore.get(taskId);
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public TaskDataSourceVO getOriginDataSource() {
		return OriginDataSource;
	}

	public void setOriginDataSource(TaskDataSourceVO originDataSource) {
		OriginDataSource = originDataSource;
	}

	public TaskDataSourceVO getObjDataSource() {
		return ObjDataSource;
	}

	public void setObjDataSource(TaskDataSourceVO objDataSource) {
		ObjDataSource = objDataSource;
	}

	public TaskCycleVO getTaskCycle() {
		return taskCycle;
	}

	public void setTaskCycle(TaskCycleVO taskCycle) {
		this.taskCycle = taskCycle;
	}
	
	

}
