package fr.utbm.cantine;

import fr.utbm.cantine.utils.security.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //This is the extra function,use this to start auto-tasks. Default close.
public class CantineApplication {
    public static void main(String[] args) {
        SpringApplication.run(CantineApplication.class, args);
        System.out.println("Today token for hardware is : "+ JwtUtil.signForHardware());
    }
}
