package Common;

import BaseFolder.UI.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonFunctionPage extends BasePage {

    private final By usernameField = By.cssSelector("input#user-name");
    private final By passwordField = By.xpath("//input[@id='password']");

    public void openBrowser(String browser){
        initialize(browser);
    }

    public void logOn(String username, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);

    }

    public void clickButton(String buttonName) throws InterruptedException {
        switch (buttonName.toLowerCase()){
            case "login":
                By loginButton = By.cssSelector("input#login-button");
                wait.until(ExpectedConditions.elementToBeClickable(loginButton));
                driver.findElement(loginButton).click();
                break;
            case "menu":
                By menuButton = By.cssSelector("button#react-burger-menu-btn");
                wait.until(ExpectedConditions.elementToBeClickable(menuButton));
                driver.findElement(menuButton).click();

                break;

            case "logout":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(("//a[@tabindex='-1'][@id='logout_sidebar_link']"))));
                By logoutButton = By.cssSelector("a#logout_sidebar_link");
                wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
                driver.findElement(logoutButton).click();
                break;

            default:
                throw new IllegalArgumentException("Cannot found button name: " + buttonName);
        }
    }
}
