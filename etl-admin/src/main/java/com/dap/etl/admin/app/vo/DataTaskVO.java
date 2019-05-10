package com.dap.etl.admin.app.vo;

import com.dap.etl.admin.domain.model.task.TaskCycleVO;
import com.dap.etl.admin.domain.model.task.TaskDataSourceVO;

/**
 * 数据任务值对象
 * @author liuwei1
 *
 */
public class DataTaskVO {

	private String taskId;

	private String taskName;

	private TaskDataSourceVO OriginDataSource;

	private TaskDataSourceVO ObjDataSource;
	
	private TaskCycleVO taskCycle; 
	

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
