package BaseFolder.StepDefinition.Common;

import BaseFolder.StepDefinition.BaseDef;
import Common.CommonFunctionPage;
import RunSettings.UIRunSettings;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"BaseFolder.StepDefinition.Common"}
)

public class CommonFunctionDef extends BaseDef {

    @Given("user opens {string} and Navigates to {string} page")
    public void userOpensBrowserAndNavigatesToYourPage(String browser, String page) {
        commonFunctionPage = new CommonFunctionPage();
        uiRunSettings = UIRunSettings.returnPageValue(page);

        commonFunctionPage.openBrowser(browser.toLowerCase());
        commonFunctionPage.navigateToUrl(uiRunSettings.baseUrl);
    }

    @And("user logs in as {string} with {string}")
    public void userLogsInAsWith(String username, String password) {
        uiRunSettings = UIRunSettings.returnUsernameAndPassword(username,password);
        commonFunctionPage.logOn(uiRunSettings.username, uiRunSettings.password);
    }

    @When("users clicks the {string} button")
    public void clicksTheButton(String buttonName) throws InterruptedException {
        commonFunctionPage.clickButton(buttonName);
    }

}

