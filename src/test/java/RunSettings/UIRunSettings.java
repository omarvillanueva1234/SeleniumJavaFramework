package RunSettings;

public class UIRunSettings {
    public final String baseUrl;
    public final String username;
    public final String password;
    public final String browser;

    private UIRunSettings(String baseUrl, String username, String password, String browser) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
        this.browser = browser;
    }

    public static UIRunSettings fromSystemProperties() {
        String env = System.getProperty("env", "test");
        String rawArgs = System.getProperty("args", "");
        String browser = System.getProperty("browser", "");
        String[] argsArray = rawArgs.split(",");
        String baseUrl = "";
        String username = "";
        String password = "";

        for (String arg : argsArray) {
            String[] keyValue = arg.split("=");
            if (keyValue.length == 2) {
                switch (keyValue[0].trim()) {
                    case "baseUrl":
                        baseUrl = keyValue[1].trim();
                        break;
                    case "username":
                        username = keyValue[1].trim();
                        break;
                    case "password":
                        password = keyValue[1].trim();
                        break;
                }
            }
        }
// Comment Out for more detailed logic below.
//        if ("test".equalsIgnoreCase(env)) {
//            baseUrl = baseUrl.isEmpty() ? "https://www.saucedemo.com/" : baseUrl;
//        } else if ("production".equalsIgnoreCase(env)) {
//            baseUrl = baseUrl.isEmpty() ? "https://www.example.com" : baseUrl;
//        } else {
//            throw new IllegalArgumentException("Unknown environment: " + env);
//        }

        return new UIRunSettings(baseUrl, username, password,browser);
    }
    public static UIRunSettings returnPageValue(String pageName) {
        String baseUrl;
        switch (pageName) {
            case "Sauce Login":
                baseUrl = "https://www.saucedemo.com/";
                break;
            case "example":
                baseUrl = "https://www.example.com";
                break;
            default:
                baseUrl = fromSystemProperties().baseUrl;
        }
        UIRunSettings settings = fromSystemProperties();
        return new UIRunSettings(baseUrl, settings.username, settings.password, settings.browser);
    }

    public static UIRunSettings returnUsernameAndPassword(String username, String password) {

        switch (username){
            case "Admin":
                username = "standard_user";
                break;
            case "LockedOutUser":
                username = "locked_out_user";
                break;
            case "ProblemUser":
                username = "ProblemUser";
                break;
            case "PerformanceGlitchUser":
                username = "performance_glitch_user";
                break;
            case "User Problem":
                username = "problem_user";
                break;

            default:
                username = fromSystemProperties().username;
        }
        switch (password){
            case "Valid Password":
                password ="secret_sauce";
                break;
                case "Invalid Password":
                password ="invalid_password";
                break;
            default:
                password = fromSystemProperties().password;
        }
        UIRunSettings settings = fromSystemProperties();
        return new UIRunSettings(settings.baseUrl, username, password, settings.browser);
    }

}
