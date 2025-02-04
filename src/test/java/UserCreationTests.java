import org.junit.Before;
import org.junit.Test;

public class UserCreationTests {

    private UserSteps userSteps = new UserSteps();

    @Before
    public void prepare(){
        userSteps.createAndRegisterUser();
    }

    @Test
    public void uniqUserCreationTest(){
        userSteps.getResponse().then().statusCode(200);
    }

}
