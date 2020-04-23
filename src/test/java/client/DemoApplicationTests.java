package client;

import client.web.CoffeeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoffeeController.class)
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

}