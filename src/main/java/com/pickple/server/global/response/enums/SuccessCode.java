package com.pickple.server.global.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    TEST_OK_SUCCESS(20000, HttpStatus.OK, "test 조회 성공"),
    TEST_CREATE_SUCCESS(20100, HttpStatus.CREATED, "test 생성 성공");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
