package cucumberRunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"features"},
		glue= {"stepDefinition"},
		plugin = {"html:target/cucumber-html-report","com.cucumber.listener.ExtentCucumberFormatter:output/report.html"}
//		,dryRun = true
		,tags= {"@ValidateHomePage"}
		)
public class CucumberRunnerTest {
	
	@AfterClass
	public static void tearDown()
	{
		
		Reporter.loadXMLConfig("src/test/resources/extent-config.xml");
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", "Windows");
		Reporter.setTestRunnerOutput("Sample test runner output message");
	}
}
