package Object.Login;

import BaseFolder.UI.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    protected final By dashboardPage = By.cssSelector("div#contents_wrapper > div#inventory_container");

    public boolean isLoginSuccessful() {
        return waitElementToBeVisible(dashboardPage, 10) != null;
    }

    public String getErrorMessage() {
        By errorMessageLocator = By.cssSelector("h3[data-test='error']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return driver.findElement(errorMessageLocator).getText();
    }
}
