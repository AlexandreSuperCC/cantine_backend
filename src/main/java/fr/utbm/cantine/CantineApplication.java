package fr.utbm.cantine;

import fr.utbm.cantine.constant.CommonConstant;
import fr.utbm.cantine.utils.CommonUtils;
import fr.utbm.cantine.utils.security.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CantineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CantineApplication.class, args);

        System.out.println("Today token for hardware is : "+ JwtUtil.signForHardware(CommonConstant.Capturer.DEFAULT_PLATCAPTURER_NAME,"2022"));

    }

}
