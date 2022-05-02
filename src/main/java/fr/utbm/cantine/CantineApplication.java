package fr.utbm.cantine;

import fr.utbm.cantine.utils.security.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CantineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CantineApplication.class, args);

        System.out.println("Today token is : "+ JwtUtil.sign("mo50","2022"));

    }

}
