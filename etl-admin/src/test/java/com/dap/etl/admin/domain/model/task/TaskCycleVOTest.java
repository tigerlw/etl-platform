package com.dap.etl.admin.domain.model.task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.dap.etl.admin.domain.model.task.TaskCycleVO.TaskCycleType;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class TaskCycleVOTest {
	
	@Steps
	private TaskCycleStep taskCycleStep;
	
	@Test
	public void testIncrease()
	{
		//given
		taskCycleStep.initailTaskCycleDay();
		//when
		taskCycleStep.increaseTaskCycleDay();
		//then
		taskCycleStep.increaseTaskCycleDaySuccess();
		
	}
	
	
	public static class TaskCycleStep
	{
		private TaskCycleVO taskCycleVO;
		
		@Step("initail task cycle day")
		public void initailTaskCycleDay()
		{
			LocalDateTime startDate = LocalDateTime.of(2018, 1, 1, 1, 0);
			Long startTime = startDate.toEpochSecond(ZoneOffset.ofHours(0))*1000;
			
			LocalDateTime endDate = LocalDateTime.of(2019, 6, 1, 1, 0);
			Long endTime = endDate.toEpochSecond(ZoneOffset.ofHours(0))*1000;
			
			TaskCycleType cycleType = TaskCycleType.DAY;
			
			taskCycleVO = new TaskCycleVO(startTime,endTime,cycleType,1);
		}
		
		@Step("increase task cycle day")
		public void increaseTaskCycleDay()
		{
			taskCycleVO = taskCycleVO.increaseCycle();
		}
		
		
		@Step("increase task cycle day success")
		public void increaseTaskCycleDaySuccess()
		{

			LocalDateTime startDate = LocalDateTime.ofEpochSecond(taskCycleVO.getCurrentTime()/1000, 0, ZoneOffset.ofHours(0));
			
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			String result = df.format(startDate);
			
			assertEquals("success",result,"2018-01-02");
			
		}
		
	}

}
