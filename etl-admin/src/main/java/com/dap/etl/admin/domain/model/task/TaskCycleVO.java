package com.dap.etl.admin.domain.model.task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


/**
 * 任务周期
 * @author liuwei1
 *
 */
public class TaskCycleVO {
	
	//任务开始时间
	private Long startTime;
	
	//任务结束时间
	private Long endTime;
	
	//任务周期类型
	private TaskCycleType cycleType;
	
	//任务周期值
	private int value;
	
	//任务当前时间
	private Long currentTime;
	
	public TaskCycleVO(Long startTime,Long endTime,TaskCycleType cycleType,int value)
	{
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.cycleType = cycleType;
		this.value = value;
		this.setCurrentTime(startTime);
	}
	
	public TaskCycleVO(Long startTime,Long endTime,TaskCycleType cycleType,int value,Long currentTime)
	{
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.cycleType = cycleType;
		this.value = value;
		this.setCurrentTime(currentTime);
	}
	
	public TaskCycleVO()
	{
		
	}
	
	
	public TaskCycleVO increaseCycle()
	{
		Long currentTime = increaseTime(this);
		
		TaskCycleVO taskCycle = new TaskCycleVO(this.startTime,this.endTime,this.cycleType,this.value,currentTime);
		
			
		return taskCycle;
	}
	
	
	public Long increaseTime(TaskCycleVO taskCycle)
	{
		Long result = 0L;
		
		TaskCycleType cycleType = taskCycle.getCycleType();
		
		result = cycleType.increase(taskCycle.getCurrentTime(),taskCycle.getValue());
		
		return result;
	}
	
	

	
	
	
	public TaskCycleType getCycleType() {
		return cycleType;
	}

	public void setCycleType(TaskCycleType cycleType) {
		this.cycleType = cycleType;
	}


	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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





	public Long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}




	/**
	 * 任务周期类型枚举值
	 * @author liuwei1
	 *
	 */
	public static enum TaskCycleType
	{
		MINUTE("minute", "0") {

			@Override
			Long increase(Long currentTime,int value) {
				// TODO Auto-generated method stub
				
				Long result = currentTime + value*60*1000;
				
				return result;
			}
		},
		HOUR("hour", "1") {
			@Override
			Long increase(Long currentTime,int value) {
				// TODO Auto-generated method stub
				
				Long result = currentTime + value*3600*1000;
				
				return result;
			}
		},
		DAY("day", "2") {
			@Override
			Long increase(Long currentTime,int value) {
				// TODO Auto-generated method stub
				Long result = currentTime + value*24*3600*1000;
				
				return result;
			}
		},
		MONTH("month", "3") {
			@Override
			Long increase(Long currentTime,int value) {
				// TODO Auto-generated method stub
				
				LocalDateTime now = LocalDateTime.ofEpochSecond(currentTime/1000, 0, ZoneOffset.ofHours(0)); 
				
				now = now.plusMonths(value);
				
				Long result = now.toEpochSecond(ZoneOffset.ofHours(0))*1000;
				
				
				return result;
			}
		};
		
		private final String cycleName;
	
		private final String cycleValue;
		
		private TaskCycleType(String cycleName,String cycleValue)
		{
			this.cycleName = cycleName;
			this.cycleValue = cycleValue;
		}

		public String getCycleName() {
			return cycleName;
		}

		public String getCycleValue() {
			return cycleValue;
		}
		
		
		/**
		 * 周期递增
		 * @param currentTime
		 * @param value
		 * @return
		 */
		abstract Long increase(Long currentTime,int value);
		
		
		
	}
	
	

}
