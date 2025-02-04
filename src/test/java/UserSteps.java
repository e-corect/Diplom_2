import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import practicum.UserApi;
import practicum.UserRegister;
import practicum.Utils;
import practicum.response.UserProfile;

import static practicum.Constants.USER_PWD;

public class UserSteps {

    private Response response;

    private UserRegister userProfile;

    private UserApi userApi = new UserApi();

    public Response getResponse() {
        return response;
    }

    @Step("Генерируем данные пользователя и создаем объект")
    public UserSteps createRandomUser(){
        String userName = Utils.generateFirstName();
        userProfile = new UserRegister(Utils.generateEmail(userName), USER_PWD, userName);
        return this;
    }
    @Step("Отправляем запрос на регистрацию пользователя в системе")
    public UserSteps registerUser(){
        response = userApi.userRegister(userProfile);
        return this;
    }
    @Step("Генерируем объект с данными пользователя и регистрируем его в системе")
    public UserSteps createAndRegisterUser(){
        response = createRandomUser().registerUser().response;
        return this;
    }

//    public void verifyLoginResponse(){
//
//        Assert.assertEquals(userProfile., response.as(UserProfile.class).getUser());
//    }
}
