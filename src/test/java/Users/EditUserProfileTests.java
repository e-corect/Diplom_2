package Users;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static practicum.Constants.NOT_AUTHORISED_ERROR;

public class EditUserProfileTests {

    private UserSteps userSteps = new UserSteps();

    @Before
    public void prepare(){
        userSteps.createAndRegisterUser();
    }

    @Test
    @DisplayName("Редактируем имя пользователя")
    @Description("Проверяем возможность изменить имя зарегистрированного в системе пользователя")
    public void editUserNameTest(){
        userSteps.editUserNameData().verifyEditedProfile();
    }

    @Test
    @DisplayName("Редактируем email пользователя")
    @Description("Проверяем возможность изменить email зарегистрированного в системе пользователя")
    public void editUserEmailTest(){
        userSteps.editUserEmailData().verifyEditedProfile();
    }

    @Test
    @DisplayName("Редактируем профиль пользователя от имени анонима")
    @Description("Проверка возможности отредактировать профиль пользователя от имени анонимного пользователя")
    public void notEditUserProfileTest(){
        userSteps.editUserDataNoAuth().verifyUnsuccessfulResponse(401, NOT_AUTHORISED_ERROR);
    }

    @After
    public void clear(){
        userSteps.deleteUser().getResponse().then().statusCode(202);
    }
}
