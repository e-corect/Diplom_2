import org.junit.Test;

public class OrdersCreationTests {

    private OrdersSteps ordersSteps = new OrdersSteps();

    @Test
    public void createOrderTest(){
        System.out.println(ordersSteps.getRandomIngredients(3).getIngredients()[1]);
    }
}
