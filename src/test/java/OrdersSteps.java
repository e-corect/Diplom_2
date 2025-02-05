import io.restassured.response.Response;
import practicum.OrdersApi;
import practicum.responses.Datum;
import practicum.responses.Ingredients;

import java.util.ArrayList;
import java.util.Random;

public class OrdersSteps {

    private Response response;

    private String[] ingredients;

    private OrdersApi ordersApi = new OrdersApi();

    public String[] getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getIgredientsList(){
        response = ordersApi.getIngredients();
        Ingredients responseObj = response.as(Ingredients.class);
        ArrayList<String> ingredientsList = new ArrayList<>();
        for (Datum datum : responseObj.getData()){
           ingredientsList.add(datum.get_id());
        }
        return ingredientsList;
    }

    public OrdersSteps getRandomIngredients(Integer ingrCount){
        ArrayList<String> igredientsList = getIgredientsList();
        Random rnd = new Random();
        ingredients = new String[ingrCount];
        for(int i=0;i<ingrCount;i++){
            ingredients[i] = igredientsList.get(rnd.nextInt(igredientsList.size()));
        }
        return this;
    }
}
