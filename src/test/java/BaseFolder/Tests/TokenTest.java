package BaseFolder.Tests;

import BaseFolder.API.ServicesName.Get_Product;
import BaseFolder.ConstantVariables.APIDefaults;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TokenTest extends BaseApiTest {

    @Test(groups = { "SmokeTest" })
    public void testCategory1_token_not_null_and_can_get_products() {
        System.out.println("Generated Token: " + token);
        Assert.assertNotNull(token, "Auth token should not be null");

        Get_Product getProduct = new Get_Product(runSettings.getServiceUrl());
        Response response = getProduct.getProducts(APIDefaults.DUMMY_PRODUCTS_ENDPOINT, token);

        Assert.assertEquals(response.getStatusCode(), 200, "Products endpoint should return 200");
        Assert.assertFalse(response.asString().isEmpty(), "Products response body should not be empty");
    }

    @Test(groups = { "SmokeTest" })
    public void testCategory2_token_not_empty() {
        System.out.println("Generated Token: " + token);

        Get_Product getProduct = new Get_Product(runSettings.getServiceUrl());
        Response response = getProduct.getProducts(APIDefaults.DUMMY_PRODUCTS_ENDPOINT+"/1", token);
        Assert.assertEquals(response.getStatusCode(), 200, "Products endpoint should return 200");
        Assert.assertFalse(response.asString().isEmpty(), "Products response body should not be empty");
        Assert.assertFalse(token.isEmpty(), "Auth token should not be empty");
    }

    @Test(groups = { "Regression" })
    public void testCategory3_token_length_is_reasonable() {
        System.out.println("Generated Token: " + token);
        Assert.assertTrue(token.length() > 10, "Auth token length should be > 10");
    }
}
