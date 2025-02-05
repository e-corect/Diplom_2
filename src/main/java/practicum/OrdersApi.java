package practicum;

import io.restassured.response.Response;

import static practicum.Constants.INGREDIENTS_PATH;
import static practicum.Constants.ORDERS_PATH;

public class OrdersApi extends BaseHTTPClient {

    public Response getUserOrders(String authToken){
        return makeGetRequest(ORDERS_PATH, authToken);
    }

    public Response createOrder(String authToken, Order body){
        return makePostRequest(ORDERS_PATH, authToken, body);
    }

    public Response getIngredients(){
        return makeGetRequest(INGREDIENTS_PATH);
    }
}
