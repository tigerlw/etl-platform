package com.dap.etl.platform.admin.stories;

import org.jbehave.core.steps.InjectableStepsFactory;

import net.serenitybdd.jbehave.SerenityStepFactory;
import net.serenitybdd.jbehave.SerenityStories;

public class MySerenityStories extends SerenityStories{
	
	public MySerenityStories(String storyName)
	{
		this.findStoriesCalled(storyName);
	}
	
	public InjectableStepsFactory stepsFactory() {
        return SerenityStepFactory.withStepsFromPackage(this.getClass().getPackage().getName(), configuration()).andClassLoader(getClassLoader());
    }

}
