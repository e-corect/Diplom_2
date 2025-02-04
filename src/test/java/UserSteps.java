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

    private UserProfile userProfile;

    private UserRegister userRegister;

    private UserApi userApi = new UserApi();

    public Response getResponse() {
        return response;
    }

    public UserSteps setUserRegister(UserRegister userRegister){
        this.userRegister = userRegister;
        return this;
    }

    @Step("Генерируем данные пользователя и создаем объект")
    public UserSteps createRandomUser(){
        String userName = Utils.generateFirstName();
        userRegister = new UserRegister(Utils.generateEmail(userName), USER_PWD, userName);
        return this;
    }
    @Step("Отправляем запрос на регистрацию пользователя в системе")
    public UserSteps registerUser(){
        response = userApi.userRegister(userRegister);
        return this;
    }
    @Step("Генерируем объект с данными пользователя и регистрируем его в системе")
    public UserSteps createAndRegisterUser(){
        response = createRandomUser().registerUser().response;
        userProfile = response.as(UserProfile.class);
        return this;
    }

    public UserSteps deleteUser(){
        response = userApi.deleteUser(userProfile.getAccessToken().split(" ")[1]);
        return this;
    }

    public void verifyLoginResponse(){
        response.then().statusCode(200);
        Assert.assertTrue(response.as(UserProfile.class).isSuccess());
        Assert.assertEquals(userRegister.getEmail(), response.as(UserProfile.class).getUser().getEmail());
        Assert.assertEquals(userRegister.getName(), response.as(UserProfile.class).getUser().getName());
    }

    @Step("Проверка неуспешного ответа сервера на соответствие ожидаемым критериям")
    public void verifyUnsuccessResponse(Integer expectedStatusCode, String expectedErrorMessage){

        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
        Assert.assertEquals(expectedErrorMessage, response.getBody().jsonPath().get("message"));
    }
}
