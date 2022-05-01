package fr.utbm.cantine.utils;
/**
 * @Type CommonUtils.java
 * @Desc Common utils
 * @author yuan.cao@utbm.fr
 * @date 01/05/2022 13:49
 * @version 1.0
 */
public class CommonUtils {

    /**
    * @DESC return the closest floating-point value that is equal to a mathematical integer and cast it.
    * @param Double
    * @return Integer
    * @data 02/05/2022 00:28
    * @author yuan.cao@utbm.fr
    **/
    public static Integer doubleToInteger(Double d){
        if (d==null){
            return null;
        }
        double di = Math.rint(d);
        return Integer.valueOf( (int) di);
    }

}
