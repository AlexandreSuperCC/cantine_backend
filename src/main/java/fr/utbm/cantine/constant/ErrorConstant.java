package fr.utbm.cantine.constant;

/**
 * @Type ErrorConstant.java
 * @Desc error constant
 * @author yuan.cao@utbm.fr
 * @date 01/05/2022 13:41
 * @version 1.0
 */
public interface ErrorConstant {
    class Common {
        public static final String PARAM_IS_EMPTY = "the parameter is empty";
    }
    class Security {
        public static final String JWT_UNAUTHORIZED = "wrong token";
    }
    class Login {
        public static final String LOGIN_USERNAME_EMPTY = "username is empty";
        public static final String USERNAME_PASSWORD_ERROR = "username doesn't exist or password is incorrect";
        public static final String MAKE_TOKEN_ERROR = "making token fails";
    }
    class HttpStatus {
        public final static Integer NO_TOKEN = 410;
        public final static Integer JWT_UNAUTHORIZED = 412;
    }
}
