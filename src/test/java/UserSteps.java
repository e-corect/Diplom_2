import io.qameta.allure.Step;
import io.restassured.response.Response;
import practicum.OrdersApi;
import practicum.UserApi;
import practicum.UserRegister;
import practicum.Utils;

import static practicum.Constants.USER_PWD;

public class UserSteps {

    private UserApi userApi;

    private Response response;

    private UserRegister userProfile;

    @Step("")
    public UserSteps createRandomUser(){
        String userName = Utils.generateFirstName();
        userProfile = new UserRegister(Utils.generateEmail(userName), USER_PWD, userName);
        return this;
    }

    public UserSteps registerUser(){
        response = userApi.userRegister(userProfile);
        return this;
    }


}
