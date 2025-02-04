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
    public void uniqUserCreationTest(){
        userSteps.verifyLoginResponse();
    }

    @Test
    public void createExistedUser(){
        userSteps.registerUser().verifyUnsuccessResponse(403, USER_EXIST_ERROR);
    }

    @After
    public void clear(){
        userSteps.deleteUser();
    }
}
