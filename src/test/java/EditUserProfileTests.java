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
    public void editUserProfileTest(){
        userSteps.editUserData().verifyEditedProfile();
    }

    @Test
    public void notEditUserProfileTest(){
        userSteps.editUserDataNoAuth().verifyUnsuccessfulResponse(401, NOT_AUTHORISED_ERROR);
    }

    @After
    public void clear(){
        userSteps.deleteUser().getResponse().then().statusCode(202);
    }
}
