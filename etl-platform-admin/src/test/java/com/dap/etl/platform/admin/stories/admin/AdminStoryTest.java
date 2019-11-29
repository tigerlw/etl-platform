package com.dap.etl.platform.admin.stories.admin;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.dap.etl.platform.admin.steps.AddToolSteps;
import com.dap.etl.platform.admin.stories.MySerenityStories;

import net.thucydides.core.annotations.Steps;

public class AdminStoryTest extends MySerenityStories{
	
	@Steps
	private AddToolSteps toolSteps;
	
	public AdminStoryTest()
	{
		super("add_new_todos.story");
	}
	
	
	@Given("I need to $doSomething")
	public void i_need_to(String doSomething) throws Throwable {
		//jane.opens_the_todo_application();
	}

	/*@Steps
	ATodoUser jane;*/

	@When("I add the todo action '$actionName'")
	@Alias("I have added the todo action 'actionName'")
	public void i_add_the_todo_action(String actionName) throws Throwable {
		//jane.adds_an_action_called(actionName);
		//addToolSteps.getTools();
		toolSteps.getTools();
	}

	@Then("'$action' should appear in my todo list")
	@Alias("'$action' should be recorded in my todo list")
	public void action_should_appear_in_my_todo_list(String action) throws Throwable {
		//jane.should_see_the_todo_action(action);
	}
	
	

}
