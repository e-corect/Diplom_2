import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.UserRegister;
import practicum.Utils;

import static practicum.Constants.USER_PWD;
import static practicum.Constants.REQUIRED_FIELDS_ERROR;

@RunWith(Parameterized.class)
public class UserCreationParametrizedTest {

    private UserRegister userRegisterObj;
    private Integer expectedStatusCode;
    private UserSteps userSteps = new UserSteps();

    public UserCreationParametrizedTest(UserRegister userRegisterObj, Integer expectedStatusCode) {
        this.userRegisterObj = userRegisterObj;
        this.expectedStatusCode = expectedStatusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {

        return new Object[][]{
                {new UserRegister(Utils.generateEmail(null), USER_PWD, ""), 403},
                {new UserRegister("", USER_PWD, Utils.generateFirstName()), 403},
                {new UserRegister(Utils.generateEmail(null), "", Utils.generateFirstName()), 403},
        };
    }

    @Before
    public void prepare(){
        userSteps.setUserRegister(userRegisterObj).registerUser();
    }

    @Test
    public void createUserWithoutAttribute(){
        userSteps.verifyUnsuccessfulResponse(403, REQUIRED_FIELDS_ERROR);
    }
}
