import practicum.Order;
import org.junit.Assert;
import practicum.OrdersApi;
import io.qameta.allure.Step;
import practicum.Utils;
import practicum.responses.Datum;
import practicum.responses.Ingredients;
import io.restassured.response.Response;

import java.util.Random;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;


public class OrdersSteps {

    private Response response;

    private Order order;

    private OrdersApi ordersApi = new OrdersApi();

    @Step("Получаем список доступных ингредиентов, формируем список из идентификаторов")
    public ArrayList<String> getIgredientsList(){
        response = ordersApi.getIngredients();
        Ingredients responseObj = response.as(Ingredients.class);
        ArrayList<String> ingredientsList = new ArrayList<>();
        for (Datum datum : responseObj.getData()){
           ingredientsList.add(datum.get_id());
        }
        return ingredientsList;
    }

    @Step("Выбираем из общего списка нужное количество ингредиентов и складываем в список")
    public OrdersSteps getRandomIngredients(Integer ingrCount){
        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<String> igredientsList = getIgredientsList();
        Random rnd = new Random();
        for(int i=0;i<ingrCount;i++){
            ingredients.add(igredientsList.get(rnd.nextInt(igredientsList.size())));
        }
        order = new Order(ingredients);
        return this;
    }

    @Step("Создаем заказ из случайно выбранных ингредиентов")
    public OrdersSteps createOrder(String authToken){
        response = ordersApi.createOrder(authToken, order);
        return this;
    }

    public OrdersSteps createOrderWithWrongIngredient(String authToken){
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add(Utils.generateLastName().toLowerCase());
        order = new Order(ingredients);
        response = ordersApi.createOrder(authToken, order);
        return this;
    }

    @Step("Проверяем успешный ответ системы")
    public void verifySuccessResponse(){
        Assert.assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().jsonPath().get("success"));

    }

    @Step("Проверяем неуспешный ответ системы")
    public void verifyUnSuccessResponse(int expctdStatusCode, String expctdErrorMsg){
        Assert.assertEquals(expctdStatusCode, response.getStatusCode());
        if (expctdStatusCode == 500){
            Assert.assertEquals(expctdErrorMsg, response.getBody().htmlPath().get("html.body.pre"));
        } else {
            Assert.assertEquals(expctdErrorMsg, response.getBody().jsonPath().get("message"));
            assertFalse(response.getBody().jsonPath().get("success"));}
    }


}
