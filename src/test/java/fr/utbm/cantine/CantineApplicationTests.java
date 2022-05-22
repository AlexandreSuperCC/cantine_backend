package fr.utbm.cantine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class CantineApplicationTests {

    @Test
    void getToken() {
        System.out.println("hello");
    }

    @Test
    void contextLoads() {
    }

}
