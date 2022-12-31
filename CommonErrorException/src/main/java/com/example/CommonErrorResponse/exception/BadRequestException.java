package com.example.CommonErrorResponse.exception;

import com.example.CommonErrorResponse.ErrorCode;
import lombok.Getter;

@Getter
public class BadRequestException extends BaseException{
    private String message;

    public BadRequestException(String message) {
        super(ErrorCode._BAD_REQUEST);
        this.message = message;
    }

    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode);
        this.message = message;
    }

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

}
