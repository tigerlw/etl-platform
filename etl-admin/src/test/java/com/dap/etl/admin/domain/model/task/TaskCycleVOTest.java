package com.dap.etl.admin.domain.model.task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.dap.etl.admin.domain.model.task.TaskCycleVO.TaskCycleType;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;

import static org.junit.Assert.*;

@RunWith(SerenityParameterizedRunner.class)
public class TaskCycleVOTest {
	
	@Steps
	private TaskCycleStep taskCycleStep;
	
	private TaskCycleType type;
	
	private int value;
	
	private String format;
	
	private String expectValue;
	
	@TestData
	public static Collection<Object[]> testData(){
		
		return Arrays.asList(new Object[][]{
            {TaskCycleType.MINUTE, 1,"yyyy-MM-dd HH:mm","2018-01-01 01:01"},
            {TaskCycleType.HOUR, 1,"yyyy-MM-dd HH","2018-01-01 02"},
            {TaskCycleType.DAY, 1,"yyyy-MM-dd","2018-01-02"},
            {TaskCycleType.MONTH, 1,"yyyy-MM-dd","2018-02-01"}
    });
	}
	
	public TaskCycleVOTest(TaskCycleType type,int value,String format,String expectValue)
	{
		this.type = type;
		this.value = value;
		this.format = format;
		this.expectValue = expectValue;
	}
	
	
	@Test
	public void testIncrease()
	{
		//given
		taskCycleStep.initailTaskCycleDay(type,value);
		//when
		taskCycleStep.increaseTaskCycle();
		//then
		taskCycleStep.increaseTaskCycleDaySuccess(format,expectValue);
		
	}
	
	
	public static class TaskCycleStep
	{
		private TaskCycleVO taskCycleVO;
		
		@Step("initail task cycle type:{0},value:{1}")
		public void initailTaskCycleDay(TaskCycleType type,int value)
		{
			
			LocalDateTime startDate = LocalDateTime.of(2018, 1, 1, 1, 0);
			Long startTime = startDate.toEpochSecond(ZoneOffset.ofHours(0))*1000;
			
			LocalDateTime endDate = LocalDateTime.of(2019, 6, 1, 1, 0);
			Long endTime = endDate.toEpochSecond(ZoneOffset.ofHours(0))*1000;
			
			taskCycleVO = new TaskCycleVO(startTime,endTime,type,value);
		}
		
		@Step("increase task cycle")
		public void increaseTaskCycle()
		{
			taskCycleVO = taskCycleVO.increaseCycle();
		}
		
		
		@Step("increase task cycle success,format:{0},expectValue:{1}")
		public void increaseTaskCycleDaySuccess(String format,String expectValue)
		{

			LocalDateTime startDate = LocalDateTime.ofEpochSecond(taskCycleVO.getCurrentTime()/1000, 0, ZoneOffset.ofHours(0));
			
			DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
			
			String result = df.format(startDate);
			
			assertEquals("success",expectValue,result);
			
		}
		
	}

}
