package com.dap.etl.platform.admin.stories.filter;

import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;


import com.dap.etl.platform.admin.stories.MySerenityStories;



public class FilterStoryTest extends MySerenityStories{
	
	public FilterStoryTest()
	{
		super("filter_todos_by_status.story");
	}
	
	
	
	@Given("$actor has a todo list containing $thingsToDo")
    public void has_a_todo_list_containing(String actor, List<String> thingsToDo) throws Throwable {
      
    }


    @When("$actor deletes the todo action $action")
    public void delete_the_todo_action(String actor, String action) throws Throwable {
        
    }

    @Then("$actor's todo list should contain $expectedTodos")
    public void my_todo_list_should_contain(String actor, List<String> expectedTodos) throws Throwable {
       
    }

    private Object[] theActionsIn(List<String> expectedTodos) {
        return expectedTodos.toArray();
    }

    @Given("$actor has marked the $itemName action as complete$")
    public void i_have_marked_the_action_as_complete(String actor, String itemName) throws Throwable {
        
    }
    
    @When("$name consults the $status tasks$")
    public void i_delete_the_todo_action(String name, String status) throws Throwable {
        /*theActorNamed(name).attemptsTo(FilterItems.byStatus(status));*/
    }

}
