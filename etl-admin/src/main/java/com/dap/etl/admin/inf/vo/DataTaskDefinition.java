package com.dap.etl.admin.inf.vo;

/**
 * 任务定义请求对象
 * @author liuwei1
 *
 */
public class DataTaskDefinition {
	
	private String taskId;
	private String taskName;
	private String originId;
	private String orgTable;
	private String objId;
	private String objTable;
	
	private Long startTime;
	private Long endTime;
	private String cycle;
	private Integer value;
	
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
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public String getOrgTable() {
		return orgTable;
	}
	public void setOrgTable(String orgTable) {
		this.orgTable = orgTable;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getObjTable() {
		return objTable;
	}
	public void setObjTable(String objTable) {
		this.objTable = objTable;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	

}
