package Users;

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
    public void successfulLogin(){
        userSteps.userLogin().verifySuccessfulUserLoginResponse();
    }

    @Test
    public void unsuccessfulLugin(){
        userSteps.userLogin(userSteps.getUserRegister().getEmail()+"1",
            userSteps.getUserRegister().getPwd()+"1")
            .verifyUnsuccessfulResponse(401, INCORRECT_CREDENTIALS_ERROR);
    }

    @After
    public void clear(){
        userSteps.deleteUser().getResponse().then().statusCode(202);
    }
}
