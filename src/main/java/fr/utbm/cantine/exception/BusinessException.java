package fr.utbm.cantine.exception;

/**
 * @Type BusinessException.java
 * @Desc unified exception handler class
 * @author yuan.cao@utbm.fr
 * @date 26/04/2022 13:12
 * @version 1.0
 */
public class BusinessException extends Exception{
    protected String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String[] errMessageArguments;

    public static BusinessException withErrorCode(String errorCode){
        BusinessException businessException = new BusinessException();
        businessException.errorCode = errorCode;
        return businessException;
    }

    public BusinessException withErrMessageArguments(String... errMessageArguments) {
        this.errMessageArguments = errMessageArguments;
        return this;
    }
}
