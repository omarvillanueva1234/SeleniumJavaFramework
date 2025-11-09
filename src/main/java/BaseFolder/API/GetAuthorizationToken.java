package BaseFolder.API;

import BaseFolder.Models.DataMapper.UserTokenDTO;
import io.restassured.response.Response;
import org.json.JSONObject;

public class GetAuthorizationToken extends APIBase {

    private final String endpoint;
    private final String sproutHrDomain;
    private final String username;
    private final String password;

    /**
     * @Author: Yul Omar Villanueva
     * @param baseUrl - Service URL
     * @param endpoint - Service Endpoint
     * @param sproutHrDomain - Sprout Domain (Internal Use Only)
     * @param username - Username for authentication
     * @param password - Password for authentication
     * @Description - This constructor initializes the GetAuthorizationToken class with necessary parameters.
     */
    public GetAuthorizationToken(String baseUrl, String endpoint, String sproutHrDomain, String username, String password) {
        super(baseUrl);
        this.endpoint = endpoint;
        this.sproutHrDomain = sproutHrDomain; // Internal Use Only
        this.username = username;
        this.password = password;
    }

    /**
     * @Author: Yul Omar Villanueva
     * @Description - This method retrieves the user authorization token by sending a POST request with user credentials.
     * @return
     */
    public String getUserAuthToken() {

        UserTokenDTO userToken = new UserTokenDTO(
                sproutHrDomain.replace("https://", "").replace("http://", ""),
                username,
                password
        );

        Response response = post(endpoint, userToken);

        int status = response.getStatusCode();
        String responseBody = response.asString();

        System.out.println("Status Code Value: " + status);
        System.out.println("Response Body Value: " + responseBody);

        return getToken(responseBody);
    }

    /**
     * @Author : Yul Omar Villanueva
     * @Description - This method extracts the token from the response body.
     * @param responseBody - The response body as a string.
     * @return - The extracted token.
     */
    private static String getToken(String responseBody) {
        JSONObject json = new JSONObject(responseBody);

        String token = null;
        if (json.has("access_token")) {
            token = json.getString("access_token"); // SproutHR format
        } else if (json.has("accessToken")) {
            token = json.getString("accessToken");
        } else if (json.has("token")) { // for DummyJSON
            token = json.getString("token");
        }

        if (token == null) {
            throw new RuntimeException("No valid token field found in response!");
        }
        return token;
    }
}
