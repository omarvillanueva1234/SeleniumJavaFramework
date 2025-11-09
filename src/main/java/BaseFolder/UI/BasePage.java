package BaseFolder.UI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BasePage {
    protected static WebDriver driver = null;
    protected static WebDriverWait wait;
    static JavascriptExecutor executor = (JavascriptExecutor) driver;
    static ChromeOptions chromeOptions;
    public static void initialize(String browserType){

        try{

            switch (browserType) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    System.out.println("Initializing Chrome browser");
                    WebDriverManager.chromedriver().setup();

                    chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

                    // optional, but kiosk sometimes makes popups more annoying, you can remove it while debugging
                    // chromeOptions.addArguments("--kiosk");

                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-save-password-bubble");
                    chromeOptions.addArguments("--disable-password-manager-reauthentication");

                    // IMPORTANT: disable the password leak / check features
                    chromeOptions.addArguments(
                            "--disable-features=PasswordManagerOnboarding," +
                                    "PasswordManagerLeakDetection," +
                                    "PasswordCheck," +
                                    "PasswordManagerSettingsReconciliation");

                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    // extra safety
                    prefs.put("profile.password_manager_leak_detection", false);
                    chromeOptions.setExperimentalOption("prefs", prefs);

                    // use a clean profile so there are no stored passwords
                    chromeOptions.addArguments("user-data-dir=" + System.getProperty("user.dir") + "/chrome-profile-automation");

                    driver = new ChromeDriver(chromeOptions);
                    break;


                case "headless-chrome":
                    chromeOptions = new ChromeOptions();
                    System.out.println("Initializing Headless Chrome browser");
                    chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                    chromeOptions.addArguments("--kiosk");
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");

                    driver = new ChromeDriver(chromeOptions);

                    break;

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    }

    public void navigateToUrl(String url){
        driver.get(url);
    }

    public static WebElement waitElementToBeVisible(By locator, int duration) {
        WebElement element = null;
        boolean result = false;
        int attempts = 0;
        while (attempts <= 3 && result != true) {
            try {
                element = new WebDriverWait(driver, Duration.ofSeconds(duration)).ignoring(StaleElementReferenceException.class)
                        .ignoring(NoSuchElementException.class)
                        .until(ExpectedConditions.visibilityOfElementLocated(locator));
                result = true;
            } catch (Exception e) {
                e.getMessage();
            }
            attempts++;
        }
        return element;

    }
}
