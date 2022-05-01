package fr.utbm.cantine.constant;
/**
 * @Type CommonConstant.java
 * @Desc common constant
 * @author yuan.cao@utbm.fr
 * @date 01/05/2022 14:06
 * @version 1.0
 */
public interface CommonConstant {
    class Calculation {
        /**
         * used in plat weight calculation,
         * allow the difference of the current plat weight and the last time is within the given degree of accuracy
         */
        public static final Double DEGREE_ACCURACY  = 0.1;

    }
    class Capturer {
        /**
         * default plat capturer name
         */
        public static final String DEFAULT_PLATCAPTURER_NAME = "STM32F401";
    }
}
