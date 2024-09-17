package com.moneybook.api.global.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "사용자를 찾을 수 없습니다."),
    INVALID_INPUT_VALUE(BAD_REQUEST, "유효하지 않은 입력 값입니다."),

    //회원가입
    ACCOUNT_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 계정입니다."),
    PASSWORD_TOO_SHORT(HttpStatus.BAD_REQUEST, "비밀번호는 최소 10자 이상이어야 합니다."),
    PASSWORD_LACKS_VARIETY(HttpStatus.BAD_REQUEST, "숫자, 문자, 특수문자 중 2가지 이상을 포함해야 합니다."),
    PASSWORD_SIMILAR_TO_PERSONAL_INFO(HttpStatus.BAD_REQUEST, "비밀번호는 다른 개인 정보와 유사할 수 없습니다."),
    PASSWORD_HAS_SEQUENTIAL_CHARS(HttpStatus.BAD_REQUEST, "3회 이상 연속되는 문자는 사용할 수 없습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
