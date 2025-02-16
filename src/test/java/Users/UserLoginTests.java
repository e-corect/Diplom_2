package Users;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static practicum.Constants.INCORRECT_CREDENTIALS_ERROR;

public class UserLoginTests {

    private UserSteps userSteps = new UserSteps();

    @Before
    public void prepare(){
        userSteps.createAndRegisterUser();
    }

    @Test
    @DisplayName("Проверка возможности успешно войти в систему")
    @Description("Проверяем возможность войти в систему с реквизитами только что созданного пользователя")
    public void successfulLogin(){
        userSteps.userLogin().verifySuccessfulUserLoginResponse();
    }

    @Test
    @DisplayName("Проверка возможности войти в систему с неверными реквизитами")
    @Description("Проверяем ответ системы на попытку входа под неверными реквизитами пользователя")
    public void unsuccessfulLogin(){
        userSteps.userLogin(userSteps.getUserRegister().getEmail()+"1",
            userSteps.getUserRegister().getPwd()+"1")
            .verifyUnsuccessfulResponse(401, INCORRECT_CREDENTIALS_ERROR);
    }

    @After
    public void clear(){
        userSteps.deleteUser().getResponse().then().statusCode(202);
    }
}
