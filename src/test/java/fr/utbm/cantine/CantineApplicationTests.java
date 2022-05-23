package fr.utbm.cantine;

import fr.utbm.cantine.utils.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class CantineApplicationTests {

    @Test
    void getToken() {
        System.out.println("Today token for hardware is : "+ JwtUtil.signForHardware());
    }

    @Test
    void contextLoads() {
    }

}
