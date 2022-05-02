package fr.utbm.cantine.utils.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @Type JwtUtil.java
 * @Desc common encryption utils
 * @author yuan.cao@utbm.fr
 * @date 01/05/2022 22:07
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "jwt.token")
@PropertySource(value = {"classpath:jwt.properties"})
public class JwtUtil {
    private static String privateKey;
    /**
     * the time before expired for hardware
     */
    private static String limitTimeHardware;
    private static String limitTimeSession;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        JwtUtil.privateKey = privateKey;
    }

    public String getLimitTimeHardware() {
        return limitTimeHardware;
    }

    public String getLimitTimeSession() {
        return limitTimeSession;
    }

    public void setLimitTimeHardware(String limitTimeHardware) {
        JwtUtil.limitTimeHardware = limitTimeHardware;
    }

    public void setLimitTimeSession(String limitTimeSession) {
        JwtUtil.limitTimeSession = limitTimeSession;
    }

    /**
     * Generate signature for hardware
     * Public key encryption and private key decryption; Private key signature, public key signature verification.
     * @param hardwareName the name of hardware
     * @param userId the id of the user
     * @return the token created for hardware
     */
    public static String signForHardware(String hardwareName,String userId){
        try {

            assert limitTimeHardware!=null&&privateKey!=null;

            //expired time
            Date date = new Date( System.currentTimeMillis()+Long.parseLong(limitTimeHardware));
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            //set header info
            Map<String,Object> header = new HashMap<>(2);
            header.put("typ","JWT");
            header.put("alg","HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("hardwareName",hardwareName)
                    .withClaim("userId",userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
    * @DESC general sign method
    * @return the token created
    * @data 02/05/2022 16:49
    * @author yuan.cao@utbm.fr
    **/
    public static String sign(String username, String userId){
        try {

            assert limitTimeSession!=null&&privateKey!=null;

            //expired time
            Date date = new Date(System.currentTimeMillis()+Long.parseLong(limitTimeSession));
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            //set header info
            Map<String,Object> header = new HashMap<>(2);
            header.put("typ","JWT");
            header.put("alg","HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("loginName",username)
                    .withClaim("userId",userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * verify the token is valid or not
     * MessageDigest.isEqual(this.createSignatureFor(algorithm, secretBytes, contentBytes), signatureBytes);
     * @param
     * @return
     */
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

}


