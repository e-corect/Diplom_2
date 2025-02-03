import org.junit.Before;

public class UserCreationTests {

    private UserSteps userSteps;

    @Before
    public void prepare(){
        userSteps.createRandomUser();
    }


}
