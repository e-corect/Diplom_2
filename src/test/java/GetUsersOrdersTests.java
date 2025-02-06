import org.junit.After;
import org.junit.Before;

public class GetUsersOrdersTests {

    private UserSteps userSteps = new UserSteps();

    private OrdersSteps ordersSteps = new OrdersSteps();

    @Before
    public void prepare(){
        userSteps.createAndRegisterUser();
    }

    public void unauthorisedUsersOrdersTest(){

    }

    @After
    public void clear(){
        userSteps.deleteUser();
    }
}
