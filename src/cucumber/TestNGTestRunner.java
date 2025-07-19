package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//With the help of Cucumber Options we can call .feature file and StepDefinition class.
//Then run the code.

@CucumberOptions(features="src/cucumber", glue="cucumberStepDefinitions",
monochrome=true, tags= "@Regression", plugin= {"html:target/cucumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

	
	
}
