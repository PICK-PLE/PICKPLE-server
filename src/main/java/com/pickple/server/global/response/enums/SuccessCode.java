package com.pickple.server.global.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    // 200 OK
    LOGIN_SUCCESS(20000, HttpStatus.OK, "소셜 로그인 성공"),
    ISSUE_ACCESS_TOKEN_SUCCESS(20001, HttpStatus.OK, "액세스 토큰 재발급 성공"),
    USER_DELETE_SUCCESS(20002, HttpStatus.OK, "회원 삭제 성공"),
    LOGOUT_SUCCESS(20003, HttpStatus.OK, "로그아웃 성공"),
    ALL_CATEGORY_GET_SUCCESS(20004, HttpStatus.OK, "카테고리 전체 조회 성공"),
    SUBMITTER_POST_SUCCESS(20005, HttpStatus.OK, "호스트 승인 신청 성공"),
    MOIM_DETAIL_GET_SUCCESS(20006, HttpStatus.OK, "모임 상세 정보 조회 성공"),
    HOST_DETAIL_GET_SUCCESS(20007, HttpStatus.OK, "호스트 정보 조회 성공"),
    MOIM_SUBMISSION_POST_SUCCESS(20007, HttpStatus.OK, "모임 참여 신청 성공"),
    PRESIGNED_URL_GET_SUCCESS(20008, HttpStatus.OK, "presigned url 발급 성공"),
    SUBMITTED_MOIM_DETAIL_GET_SUCCESS(20008, HttpStatus.OK, "신청한 모임 상세 정보 조회 성공"),
    MOIM_LIST_BY_CATEGORY_GET_SUCCESS(20010, HttpStatus.OK, "카테고리에 해당하는 모임 조회 성공"),

    //201 Created
    MOIM_CREATE_SUCCESS(20100, HttpStatus.CREATED, "모임 개설 성공");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
