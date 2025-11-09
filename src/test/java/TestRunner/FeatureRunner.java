package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/features/"}
        , glue = {"BaseFolder.StepDefinition"}
        , dryRun = false
        , monochrome = true
        , tags = ("@SmokeTest")
        , stepNotifications = true
)
public class FeatureRunner {
}
