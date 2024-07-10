package com.pickple.server.global.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 400 Bad Request
    ENUM_VALUE_BAD_REQUEST(40000, HttpStatus.BAD_REQUEST, "요청한 값이 유효하지 않습니다."),
    AUTHENTICATION_CODE_EXPIRED(40001, HttpStatus.BAD_REQUEST, "인가 코드가 만료되었습니다."),
    SOCIAL_TYPE_BAD_REQUEST(40002, HttpStatus.BAD_REQUEST, "로그인 요청이 유효하지 않습니다."),

    // 401 Unauthorized
    ACCESS_TOKEN_EXPIRED(40100, HttpStatus.UNAUTHORIZED, "액세스 토큰이 만료되었습니다."),
    TOKEN_INCORRECT_ERROR(40102, HttpStatus.UNAUTHORIZED, "리프레시 토큰이 유효하지 않습니다."),
    EMPTY_PRINCIPAL(40103, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다"),

    // 404 Not Found
    NOT_FOUND_END_POINT(40400, HttpStatus.NOT_FOUND, "존재하지 않는 API입니다."),
    USER_NOT_FOUND(40401, HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND(40402, HttpStatus.NOT_FOUND, "해당 유저의 리프레시 토큰이 존재하지 않습니다."),
    GUEST_NOT_FOUND(40403, HttpStatus.NOT_FOUND, "존재하지 않는 게스트입니다"),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");


    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
