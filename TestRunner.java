package TestRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {".//Features/Login.feature",".//Features/Customers.feature",".//Features/Dynamic.feature"},
			glue = "StepDefinitions", 
			dryRun = false, 
			monochrome = true, 
			plugin = {"pretty", "html:test-output"},
			tags = {"@sanity, @regression, @smoke"}
			)
public class TestRunner {
}
