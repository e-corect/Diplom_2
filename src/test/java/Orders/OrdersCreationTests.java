package Orders;

import Users.UserSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
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
    @DisplayName("Создание заказа из случайного списка ингредиентов")
    @Description("Генерируем случайный список ингредиентов и создаем в системе заказ")
    public void createOrderByAuthUserTest(){
        ordersSteps.getRandomIngredients(3).createOrder(userSteps.getAuthToken()).verifySuccessResponse();

    }

    @Test
    @DisplayName("Создание заказа из случайного списка ингредиентов")
    @Description("Пытаемся создаём заказ из случайного списка ингредиентов от имени анонимного пользователя")
    public void createOrderByNotAuthUserTest(){
        ordersSteps.getRandomIngredients(5).createOrder("").verifySuccessResponse();
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    @Description("Пытаемся создать заказ без ингредиентов")
    public void createOrderWithoutIngredientsTest(){
        ordersSteps.getRandomIngredients(0)
                .createOrder(userSteps.getAuthToken())
                .verifyUnSuccessResponse(400, NO_INGREDIENTS_ERROR);
    }

    @Test
    @DisplayName("Создание заказа из неизвестного ингредиента")
    @Description("Пытаемся создать заказ из ингредиента с неизвестным идентификатором")
    public void createOrderWithWrongIngredientTest(){
        ordersSteps.createOrderWithWrongIngredient(userSteps.getAuthToken())
                .verifyUnSuccessResponse(500, INTERNAL_SERVER_ERROR);
    }

    @After
    public void clear(){
        userSteps.deleteUser();
    }
}
