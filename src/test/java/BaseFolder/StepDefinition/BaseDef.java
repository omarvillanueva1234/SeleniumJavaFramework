package BaseFolder.StepDefinition;

import Common.CommonFunctionPage;
import Object.Login.LoginPage;
import RunSettings.UIRunSettings;

public class BaseDef {

//    protected String baseURL = UIRunSettings.fromSystemProperties().baseUrl;
    protected String userName = UIRunSettings.fromSystemProperties().username;
    protected String passWord = UIRunSettings.fromSystemProperties().password;
    protected String browser1 = UIRunSettings.fromSystemProperties().browser;

    protected UIRunSettings uiRunSettings;
    protected CommonFunctionPage commonFunctionPage;
    protected LoginPage loginPage;

}
