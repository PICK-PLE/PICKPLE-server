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
    DUPLICATION_PENDING_SUBMITTER(40003, HttpStatus.BAD_REQUEST, "대기중인 호스트 승인 신청이 있습니다."),
    BAD_REQUEST(40004, HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    MISSING_REQUIRED_HEADER(40005, HttpStatus.BAD_REQUEST, "필수 헤더가 누락되었습니다."),
    MISSING_REQUIRED_PARAMETER(40006, HttpStatus.BAD_REQUEST, "필수 파라미터가 누락되었습니다."),
    DUPLICATION_MOIM_SUBMISSION(40007, HttpStatus.BAD_REQUEST, "이미 대기중인 모임입니다."),
    DUPLICATION_NICKNAME(40008, HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    MISSING_IMAGE_URL(40009, HttpStatus.BAD_REQUEST, "필수 이미지가 없습니다."),
    DUPLICATION_REVIEW(40010, HttpStatus.BAD_REQUEST, "해당 사용자가 이미 작성한 리뷰가 존재합니다"),
    NO_SUBMISSION_FOUND_FOR_REVIEW(40011, HttpStatus.BAD_REQUEST, "리뷰를 작성할 수 있는 신청이 없습니다"),
    NOT_AUTHOR(40012, HttpStatus.BAD_REQUEST, "해당 댓글의 작성자가 아닙니다."),
    DUPLICATION_APPROVE_SUBMITTER(40013, HttpStatus.BAD_REQUEST, "이미 호스트입니다."),

    // 401 Unauthorized
    ACCESS_TOKEN_EXPIRED(40100, HttpStatus.UNAUTHORIZED, "액세스 토큰이 만료되었습니다."),
    TOKEN_INCORRECT_ERROR(40102, HttpStatus.UNAUTHORIZED, "리프레시 토큰이 유효하지 않습니다."),
    EMPTY_PRINCIPAL(40103, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),

    //403 Forbidden
    NOT_ADMIN(40301, HttpStatus.UNAUTHORIZED, "관리자 계정이 아닙니다."),
    NOT_HOST(40302, HttpStatus.FORBIDDEN, "해당 모임의 호스트가 아닙니다."),

    // 404 Not Found
    NOT_FOUND_END_POINT(40400, HttpStatus.NOT_FOUND, "존재하지 않는 API입니다."),
    USER_NOT_FOUND(40401, HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND(40402, HttpStatus.NOT_FOUND, "해당 유저의 리프레시 토큰이 존재하지 않습니다."),
    GUEST_NOT_FOUND(40403, HttpStatus.NOT_FOUND, "존재하지 않는 게스트입니다"),
    MOIM_NOT_FOUND(40404, HttpStatus.NOT_FOUND, "존재하지 않는 모임입니다."),
    HOST_NOT_FOUND(40405, HttpStatus.NOT_FOUND, "존재하지 않는 호스트입니다"),
    MOIM_SUBMISSION_NOT_FOUND(40406, HttpStatus.NOT_FOUND, "해당 모임에 신청한 내역이 없습니다."),
    SUBMITTER_NOT_FOUND(40408, HttpStatus.NOT_FOUND, "호스트 승인 신청이 존재하지 않습니다."),
    NOTICE_NOT_FOUND(40409, HttpStatus.NOT_FOUND, "존재하지 않는 공지사항입니다."),
    COMMENT_NOT_FOUND(40410, HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다."),

    // 405 Method Not Allowed Error
    METHOD_NOT_ALLOWED(40500, HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 메소드입니다."),

    // 422 Unprocessable Entity
    MOIM_SUBMISSION_STATE_TRANSITION_NOT_ALLOWED(42200, HttpStatus.UNPROCESSABLE_ENTITY, "모임 신청 상태가 입금 대기 상태가 이닙니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    PRESIGNED_URL_GET_ERROR(50001, HttpStatus.INTERNAL_SERVER_ERROR, "S3 presigned url을 받아오기에 실패했습니다."),
    IMAGE_DELETE_ERROR(50002, HttpStatus.INTERNAL_SERVER_ERROR, "S3 이미지 삭제가 실패했습니다.");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

}
