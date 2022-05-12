package fr.utbm.cantine.utils;

import lombok.Data;

/**
 * @Type APIResponse.java
 * @Desc the common response of the API
 * @author yuan.cao@utbm.fr
 * @date 26/04/2022 12:26
 * @version 1.0
 */
@Data
public class APIResponse <T> {
    private String code;
    private T data;
    private String msg;
    private String token;
    private Integer userId;

    private static final String CODE_SUCCESS = "suc";
    private static final String CODE_FAIL = "fail";

    public APIResponse(String code) {
        this.code = code;
    }

    public APIResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public APIResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public APIResponse(String code, T data,String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static APIResponse fail(String msg) {
        return new APIResponse(CODE_FAIL, msg);
    }

    public static APIResponse fail(Object data,String msg) {
        return new APIResponse(CODE_FAIL, data, msg);
    }
    public static APIResponse success() {
        return new APIResponse(CODE_SUCCESS);
    }

    public static APIResponse success(Object data){
        return new APIResponse(CODE_SUCCESS,data);
    }

    public static APIResponse success(String msg){
        return new APIResponse(CODE_SUCCESS,msg);
    }

    public static APIResponse success(Object data,String msg){
        return new APIResponse(CODE_SUCCESS,data,msg);
    }

    /**
    * @DESC used for login
    * @data 12/05/2022 20:42
    * @author yuan.cao@utbm.fr
    **/
    public static APIResponse success(String token,Integer userId, Object data){
        APIResponse apiResponse = new APIResponse(CODE_SUCCESS,data);
        apiResponse.token = token;
        apiResponse.userId = userId;
        return apiResponse;
    }


}
