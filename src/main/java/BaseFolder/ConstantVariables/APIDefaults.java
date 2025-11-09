package BaseFolder.ConstantVariables;

public final class APIDefaults {

    private APIDefaults() {} // prevent instantiation

    // Supported environments
    public static final String ENV_SPROUT = "sprout";
    public static final String ENV_DUMMY  = "dummy";

    // DUMMY environment defaults
    public static final String DUMMY_BASE_URL          = "https://dummyjson.com";
    public static final String DUMMY_AUTH_ENDPOINT     = "/auth/login";
    public static final String DUMMY_PRODUCTS_ENDPOINT = "/products";

    // SPROUT environment defaults (sample values – adjust as needed)
    public static final String SPROUT_BASE_URL      = "https://ultimates-spa.hrtest.ph";
    public static final String SPROUT_AUTH_ENDPOINT = "/api/token";
}
