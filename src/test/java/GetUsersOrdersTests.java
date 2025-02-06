import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static practicum.Constants.NOT_AUTHORISED_ERROR;

public class GetUsersOrdersTests {

    private UserSteps userSteps = new UserSteps();

    private OrdersSteps ordersSteps = new OrdersSteps();

    @Before
    public void prepare(){
        Random rnd = new Random();
        userSteps.createAndRegisterUser();
        ordersSteps.getRandomIngredients(rnd.nextInt(7)).createOrder(userSteps.getAuthToken());
    }

    @Test
    public void authorizedUsersOrdersTest(){
        ordersSteps.getUsersOrders(userSteps.getAuthToken()).verifySuccessResponse();
    }

    @Test
    public void unauthorizedUsersOrdersTest(){
        ordersSteps.getUsersOrders("").verifyUnSuccessResponse(401, NOT_AUTHORISED_ERROR);
    }

    @After
    public void clear(){
        userSteps.deleteUser();
    }
}
