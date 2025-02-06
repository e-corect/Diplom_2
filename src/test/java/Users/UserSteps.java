package Users;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import practicum.UserApi;
import practicum.UserLogin;
import practicum.UserRegister;
import practicum.Utils;
import practicum.responses.UserNameMail;
import practicum.responses.UserProfile;

import static practicum.Constants.USER_PWD;

public class UserSteps {


    private Response response;

    private UserProfile userProfile;

    private UserRegister userRegister;

    private UserNameMail userNameMail;

    private UserApi userApi = new UserApi();

    public Response getResponse() {
        return response;
    }

    public String getAuthToken(){
        return userProfile.getAccessToken().split(" ")[1];
    }

    public UserRegister getUserRegister() {
        return userRegister;
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
    @Step("Удаляем пользователя из системы")
    public UserSteps deleteUser(){
        response = userApi.deleteUser(userProfile.getAccessToken().split(" ")[1]);
        return this;
    }

    @Step("Аутентификация пользователя в системе. Данные берутся из объекта Users.UserSteps")
    public UserSteps userLogin(){
        UserLogin body = new UserLogin(userRegister.getEmail(), userRegister.getPwd());
        response = userApi.userLogin(body);
        return this;
    }

    @Step("Аутентификация пользователя в системе. Данные передаются в параметрах метода")
    public UserSteps userLogin(String login, String pwd){
        UserLogin body = new UserLogin(login, pwd);
        response = userApi.userLogin(body);
        return this;
    }

    @Step("Изменяем (редактируем) поля профиля пользователя")
    public UserSteps editUserData(){
        userNameMail = new UserNameMail(userProfile.getUser().getEmail(), Utils.generateFirstName());
        response = userApi.editUserData(userProfile.getAccessToken().split(" ")[1], userNameMail);
        return this;
    }

    @Step("Изменяем (редактируем) поля профиля пользователя")
    public UserSteps editUserDataNoAuth(){
        response = userApi.editUserData("",
            new UserNameMail(userProfile.getUser().getEmail(), Utils.generateFirstName()));
        return this;
    }

    @Step("Проверяем профиль после редактирования")
    public void verifyEditedProfile(){
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(response.getBody().jsonPath().get("success"));
        Assert.assertEquals(userNameMail.getEmail(), response.getBody().jsonPath().get("user.email"));
        Assert.assertEquals(userNameMail.getName(), response.getBody().jsonPath().get("user.name"));
    }

    @Step("Прооверяем ответ сервера")
    public void verifySuccessfulUserLoginResponse(){
        response.then().statusCode(200);
        Assert.assertTrue(response.as(UserProfile.class).isSuccess());
        Assert.assertEquals(userRegister.getEmail(), response.as(UserProfile.class).getUser().getEmail());
        Assert.assertEquals(userRegister.getName(), response.as(UserProfile.class).getUser().getName());
    }

    @Step("Проверка неуспешного ответа сервера на соответствие ожидаемым критериям")
    public void verifyUnsuccessfulResponse(Integer expectedStatusCode, String expectedErrorMessage){
        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
        Assert.assertFalse(response.getBody().jsonPath().get("success"));
        Assert.assertEquals(expectedErrorMessage, response.getBody().jsonPath().get("message"));
    }
}
