package fr.utbm.cantine.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * @param request
     * @return IP Address
     */
    public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * String to token
     * @param
     * @return String
     */
    public static String getTokenStr(String str) {
        String pattern = "token:(.*?)$";
        Pattern r1 = Pattern.compile(pattern);
        Matcher m1 = r1.matcher(str);
        List<String> resList = new ArrayList<>();
        while(m1.find()){
            resList.add(m1.group(1).trim());
        }
        if(resList.size()!=1){
            return null;
        }else{
            String res = resList.toString();
            res = res.replace("[","")
                    .replace("]","");
            return res;
        }
    }

    /**
     * md5 encryption
     * @param string should be handled
     * @return string handled
     */
    public static String MD5encode(String source){
        if(StringUtils.isBlank(source)) return null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] encode = messageDigest.digest(source.getBytes());
            StringBuilder hexString = new StringBuilder();
            for(byte anEncode:encode){
                String hex = Integer.toHexString(0xff & anEncode );
                if(hex.length()==1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }catch (NoSuchAlgorithmException ignored){

        }
        return "";
    }
}
