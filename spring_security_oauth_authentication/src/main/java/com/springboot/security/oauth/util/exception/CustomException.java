package com.springboot.security.oauth.util.exception;

import com.springboot.security.oauth.util.error.ErrorResponse;

public class CustomException extends RuntimeException{

    private ErrorCode errorCode;
    private ErrorResponse errorResponse = new ErrorResponse();

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorResponse = new ErrorResponse(errorCode);
    }

    public ErrorResponse getResponse(){
        return errorResponse;
    }

    //테스트용
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
