package com.example.CommonErrorResponse.exception;

import com.example.CommonErrorResponse.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class BaseException extends RuntimeException {
    ErrorCode errorCode;
    String responseMessage;
    HttpStatus httpStatus;
    Map<String, String> data;


    public BaseException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
        this.responseMessage = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public BaseException(ErrorCode errorCode, Map<String, String> data) {
        super();
        this.errorCode = errorCode;
        this.responseMessage = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
        this.data = data;
    }

}
