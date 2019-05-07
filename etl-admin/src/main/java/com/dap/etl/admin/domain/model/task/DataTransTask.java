package com.dap.etl.admin.domain.model.task;

/**
 * 数据任务实体
 * @author liuwei1
 *
 */
public class DataTransTask {
	
	private String taskId;
	
	private String taskName;
	
	private TaskDataSourceVO OriginDataSource;
	
	private TaskDataSourceVO ObjDataSource;
	
	public boolean bulidDataTransTask(String taskId, String taskName, String originId,String orgTable, String objId,String objTable) 
	{
		this.taskId = taskId;
		this.taskName = taskName;
		
		this.OriginDataSource = new TaskDataSourceVO();
		this.OriginDataSource.buildTaskDataSource(originId, orgTable);
		this.ObjDataSource = new TaskDataSourceVO();
		this.ObjDataSource.buildTaskDataSource(objId, objTable);
		
		return true;
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
	
	

}
