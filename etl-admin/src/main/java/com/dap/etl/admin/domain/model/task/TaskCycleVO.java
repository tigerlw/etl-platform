package com.dap.etl.admin.domain.model.task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


/**
 * 任务周期
 * @author liuwei1
 *
 */
public class TaskCycleVO {
	
	/**
	 * 任务开始时间
	 */
	private Long startTime;
	
	/**
	 * 任务结束时间
	 */
	private Long endTime;
	
	/**
	 * 任务周期类型
	 */
	private TaskCycleType cycleType;
	
	/**
	 * 任务周期值
	 */
	private Integer value;
	
	/**
	 * 任务当前时间
	 */
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
	
	public TaskCycleVO(Long startTime,Long endTime,String cycle,int value)
	{
		TaskCycleType cycleType = TaskCycleType.createCycleType(cycle);
		
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.cycleType = cycleType;
		this.value = value;
		this.setCurrentTime(startTime);
	}
	


	public TaskCycleVO()
	{
		
	}
	
	/**
	 * 周期递增
	 * @return
	 */
	public TaskCycleVO increaseCycle()
	{
		Long currentTime = increaseTime(this);
		
		TaskCycleVO taskCycle = new TaskCycleVO(this.startTime,this.endTime,this.cycleType,this.value,currentTime);
		
			
		return taskCycle;
	}
	
	/**
	 * 当前周期增加函数
	 * @param taskCycle
	 * @return
	 */
	public Long increaseTime(TaskCycleVO taskCycle)
	{
		Long result = 0L;
		
		TaskCycleType cycleType = taskCycle.getCycleType();
		
		result = cycleType.increase(taskCycle.getCurrentTime(),taskCycle.getValue());
		
		return result;
	}
	
	
	@Override
    public String toString()
	{
		String result = String.format("startTime:%d , endTime:%d , cyletype:%s , cyclevalue:%d", this.startTime,
				this.endTime, this.cycleType, this.value);
		return result;
	}
	
	
	
	public TaskCycleType getCycleType() {
		return cycleType;
	}

	public void setCycleType(TaskCycleType cycleType) {
		this.cycleType = cycleType;
	}


	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
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
		/**
		 * 分钟周期
		 */
		MINUTE("minute", "0") {

			@Override
			Long increase(Long currentTime,int value) {
				// TODO Auto-generated method stub
				
				Long result = currentTime + value*60*1000L;
				
				return result;
			}
		},
		/**
		 * 小时周期
		 */
		HOUR("hour", "1") {
			@Override
			Long increase(Long currentTime,int value) {
				// TODO Auto-generated method stub
				
				Long result = currentTime + value*3600*1000L;
				
				return result;
			}
		},
		/**
		 * 天周期
		 */
		DAY("day", "2") {
			@Override
			Long increase(Long currentTime,int value) {
				// TODO Auto-generated method stub
				Long result = currentTime + value*24*3600*1000L;
				
				return result;
			}
		},
		/**
		 * 月周期
		 */
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
		
		public static TaskCycleType createCycleType(String type)
		{
			TaskCycleType result = null;
			
			switch(type)
			{
				case "minute":
					result = MINUTE;
					break;
				case "hour":
					result = HOUR;
					break;
				case "day":
					result = DAY;
					break;
				case "month":
					result = MONTH;
					break;
				default:
					result = null;
			}
			
			return result;
		}
		
		@Override
		public String toString()
		{
			return cycleName;
		}
		
		
		
	}
	
	

}
