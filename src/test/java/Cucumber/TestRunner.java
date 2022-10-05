package Cucumber;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="stepDefinition",monochrome=true,
plugin={"html:target/cucumber.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
"rerun:cucumberReports/failedScenario.txt"})
public class TestRunner extends AbstractTestNGCucumberTests{
	//tags="@Purchase"
@Override
@DataProvider(parallel=false)
public Object[][] scenarios()
{
	return super.scenarios();
	
}
	
}
