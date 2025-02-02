package practicum;

import io.restassured.response.Response;
import practicum.response.UserProfile;

import static practicum.Constants.*;

public class UserApi extends BaseHTTPClient {

    public Response userLogin(UserLogin body){
        return makePostRequest(USER_LOGIN_PATH, body);
    }

    public Response userRegister(UserRegister body){
        return makePostRequest(USER_REGISTER_PATH, body);
    }

    public Response deleteUser(String authToken){
        return makeDeleteRequest(AUTH_USER_PATH, authToken);
    }

    public Response refreshUserData(String authToken, UserRegister body){
        return makePutRequest(AUTH_USER_PATH, authToken, body);
    }
}
