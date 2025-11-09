package RunSettings;

import BaseFolder.ConstantVariables.APIDefaults;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Yul Omar Villanueva
 * @Description: Dynamically builds environment-specific run settings from system properties.
 */
public class RunSettings {

    private final String serviceUrl;
    private final String endpoint;
    private final String domain;
    private final String username;
    private final String password;

    private RunSettings(String serviceUrl,
                        String endpoint,
                        String domain,
                        String username,
                        String password) {
        this.serviceUrl = serviceUrl;
        this.endpoint   = endpoint;
        this.domain     = domain;
        this.username   = username;
        this.password   = password;
    }

    /**
     * Builds a RunSettings instance from system properties.
     * Example:
     *   -Denv=dummy -Dargs=username=testuser,password=123
     */
    public static RunSettings fromSystemProperties() {

        String env     = System.getProperty("env", APIDefaults.ENV_SPROUT);
        String rawArgs = System.getProperty("args", "");

        Map<String, String> overrides = parseArgs(rawArgs);

        if (APIDefaults.ENV_SPROUT.equalsIgnoreCase(env)) {

            return new RunSettings(
                    overrides.getOrDefault("serviceUrl", APIDefaults.SPROUT_BASE_URL),
                    overrides.getOrDefault("endpoint",   APIDefaults.SPROUT_AUTH_ENDPOINT),
                    overrides.getOrDefault("domain",
                            overrides.getOrDefault("sproutdomain", "")),
                    overrides.getOrDefault("username", ""),
                    overrides.getOrDefault("password", "")
            );

        } else if (APIDefaults.ENV_DUMMY.equalsIgnoreCase(env)) {

            return new RunSettings(
                    overrides.getOrDefault("serviceUrl", APIDefaults.DUMMY_BASE_URL),
                    overrides.getOrDefault("endpoint",   APIDefaults.DUMMY_AUTH_ENDPOINT),
                    overrides.getOrDefault("domain", ""),
                    overrides.getOrDefault("username", ""),
                    overrides.getOrDefault("password", "")
            );

        } else {
            throw new IllegalArgumentException("Unknown environment: " + env);
        }
    }

    /**
     * Parses a comma-separated key=value string into a Map.
     * Example: "username=john,password=123,domain=abc"
     */
    private static Map<String, String> parseArgs(String args) {
        Map<String, String> map = new HashMap<>();
        if (args == null || args.isEmpty()) return map;

        String[] pairs = args.split(",");
        for (String pair : pairs) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2) {
                map.put(kv[0].trim(), kv[1].trim());
            }
        }
        return map;
    }

    public String getServiceUrl() { return serviceUrl; }
    public String getEndpoint()   { return endpoint; }
    public String getDomain()     { return domain; }
    public String getUsername()   { return username; }
    public String getPassword()   { return password; }
}
