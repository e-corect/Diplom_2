import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static practicum.Constants.INTERNAL_SERVER_ERROR;
import static practicum.Constants.NO_INGREDIENTS_ERROR;

public class OrdersCreationTests {

    private UserSteps userSteps = new UserSteps();

    private OrdersSteps ordersSteps = new OrdersSteps();

    @Before
    public void prepare(){
        userSteps.createAndRegisterUser();
    }

    @Test
    public void createOrderByAuthUserTest(){
        ordersSteps.getRandomIngredients(3).createOrder(userSteps.getAuthToken()).verifySuccessResponse();

    }

    @Test
    public void createOrderByNotAuthUserTest(){
        ordersSteps.getRandomIngredients(5).createOrder("").verifySuccessResponse();
    }

    @Test
    public void createOrderWithoutIngredientsTest(){
        ordersSteps.getRandomIngredients(0)
                .createOrder(userSteps.getAuthToken())
                .verifyUnSuccessResponse(400, NO_INGREDIENTS_ERROR);
    }

    @Test
    public void createOrderWithWrongIngredientTest(){
        ordersSteps.createOrderWithWrongIngredient(userSteps.getAuthToken())
                .verifyUnSuccessResponse(500, INTERNAL_SERVER_ERROR);
    }

    @After
    public void clear(){
        userSteps.deleteUser();
    }
}
