package BaseFolder.API.ServicesName;

import BaseFolder.API.APIBase;
import io.restassured.response.Response;

/**
 * @Author : Yul Omar Villanueva
 * @Description : Handles Product-related API requests.
 */
public class Get_Product extends APIBase {

    public Get_Product(String baseUrl) {
        super(baseUrl);
    }
    /**
     * @Author : Yul Omar Villanueva
     * @Description : Generic method to get products from a specified endpoint
     * @param endpoint The API endpoint for products.
     * @param token    The authorization token.
     * @return Response object containing the API response.
     */
    public Response getProducts(String endpoint, String token) {
        return get(endpoint, token);
    }

}
