package com.moneybook.api.global.exception;

import com.moneybook.api.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String message;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public CustomException(String message, ErrorCode errorCode){
        this.message = message;
        this.errorCode = errorCode;
    }
}
