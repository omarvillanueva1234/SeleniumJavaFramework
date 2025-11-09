package BaseFolder.Tests;

import BaseFolder.API.GetAuthorizationToken;
import RunSettings.RunSettings;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

    protected String token;
    protected RunSettings runSettings;

    /**
     * @Author : Yul Omar Villanueva
     * @Description : Generate Authorization Token once per test class
     */
    @BeforeClass(alwaysRun = true)
    public void generateTokenOncePerClass() {

        runSettings = RunSettings.fromSystemProperties();

        String serviceUrl = runSettings.getServiceUrl();
        String endpoint   = runSettings.getEndpoint();
        String domain     = runSettings.getDomain();
        String username   = runSettings.getUsername();
        String password   = runSettings.getPassword();

        GetAuthorizationToken tokenGenerator =
                new GetAuthorizationToken(serviceUrl, endpoint, domain, username, password);

        token = tokenGenerator.getUserAuthToken();

    }
}
