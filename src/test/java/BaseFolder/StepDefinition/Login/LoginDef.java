package BaseFolder.StepDefinition.Login;

import BaseFolder.StepDefinition.BaseDef;
import Object.Login.LoginPage;
import dev.failsafe.internal.util.Assert;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginDef extends BaseDef {



    @Then("user is redirected to the Dashboard page")
    public void userIsRedirectedToTheDashboardPage() {
        loginPage = new LoginPage();
        Assert.isTrue(loginPage.isLoginSuccessful(), "Login was not successful, Dashboard page not displayed.");
    }

    @Then("user sees the error message {string}")
    public void userSeesTheErrorMessage(String errorMessage) {
        Assert.isTrue(loginPage.getErrorMessage().equals(errorMessage), "Error message does not match expected.");
    }
}
