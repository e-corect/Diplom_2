package Users;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.User.UserRegister;
import practicum.Utils;

import static practicum.Constants.USER_PWD;
import static practicum.Constants.REQUIRED_FIELDS_ERROR;

@RunWith(Parameterized.class)
public class UserCreationParametrizedTest {

    private UserRegister userRegisterObj;

    private UserSteps userSteps = new UserSteps();

    public UserCreationParametrizedTest(UserRegister userRegisterObj) {
        this.userRegisterObj = userRegisterObj;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {new UserRegister("", USER_PWD, Utils.generateFirstName())},
                {new UserRegister(Utils.generateEmail(null), "", Utils.generateFirstName())},
                {new UserRegister(Utils.generateEmail(null), USER_PWD, "")}
        };
    }

    @Before
    public void prepare(){
        userSteps.setUserRegister(userRegisterObj).registerUser();
    }

    @Test
    @DisplayName("Создаем пользователя без одного из атрибутов профиля")
    @Description("Пытаемся создать пользователя, не заполнив одно из обязательных полей")
    public void createUserWithoutAttribute(){
        userSteps.verifyUnsuccessfulResponse(403, REQUIRED_FIELDS_ERROR);
    }
}
