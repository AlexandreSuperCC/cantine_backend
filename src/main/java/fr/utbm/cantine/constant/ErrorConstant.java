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
}
