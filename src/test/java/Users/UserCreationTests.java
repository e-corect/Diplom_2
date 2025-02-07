package Users;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static practicum.Constants.USER_EXIST_ERROR;

public class UserCreationTests {

    private UserSteps userSteps = new UserSteps();

    @Before
    public void prepare(){
        userSteps.createAndRegisterUser();
    }

    @Test
    @DisplayName("Успешное создание уникального пользователя")
    @Description("Проверяем возможность создания в системе уникального пользователя")
    public void uniqUserCreationTest(){
        userSteps.verifySuccessfulUserLoginResponse();
    }

    @Test
    @DisplayName("Проверка возможности повторного создания пользователя")
    @Description("Проверяем возможность повторно создать в системе существующего пользователя")
    public void createExistedUser(){
        userSteps.registerUser().verifyUnsuccessfulResponse(403, USER_EXIST_ERROR);
    }

    @After
    public void clear(){
        userSteps.deleteUser().getResponse().then().statusCode(202);
    }
}
