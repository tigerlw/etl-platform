package com.dap.etl.platform.admin.steps;


import org.jbehave.core.annotations.When;



public class FilterTodoStepDefinitions {

    @When("$name consults the $status tasks$")
    public void i_delete_the_todo_action(String name, String status) throws Throwable {
        /*theActorNamed(name).attemptsTo(FilterItems.byStatus(status));*/
    }
}
