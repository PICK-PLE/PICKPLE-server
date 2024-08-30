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
    MOIM_SUBMISSION_POST_SUCCESS(20008, HttpStatus.OK, "모임 참여 신청 성공"),
    PRESIGNED_URL_GET_SUCCESS(20009, HttpStatus.OK, "presigned url 발급 성공"),
    SUBMITTED_MOIM_DETAIL_GET_SUCCESS(20010, HttpStatus.OK, "신청한 모임 상세 정보 조회 성공"),
    NOTICE_POST_SUCCESS(20011, HttpStatus.OK, "공지사항 작성 성공"),
    MOIM_LIST_BY_CATEGORY_GET_SUCCESS(20012, HttpStatus.OK, "카테고리에 해당하는 모임 조회 성공"),
    NOTICE_LIST_GET_SUCCESS(20013, HttpStatus.OK, "공지사항 리스트 조회 성공"),
    MOIM_DESCRIPTION_GET_SUCCESS(20014, HttpStatus.OK, "모임에 해당하는 소개 조회 성공"),
    MOIM_QUESTION_LIST_GET_SUCCESS(20015, HttpStatus.OK, "모임 질문 목록 조회 성공"),
    MOIM_BANNER_GET_SUCCESS(20016, HttpStatus.OK, "홈 배너 조회 성공"),
    HOST_BY_MOIM_GET_SUCCESS(20017, HttpStatus.OK, "모임에 해당하는 호스트 정보 조회 성공"),
    SUBMITTED_MOIM_LIST_BY_GUEST_GET_SUCCESS(20018, HttpStatus.OK, "게스트에 해당하는 신청한 모임 리스트 조회 성공"),
    SUBMISSION_DETAIL_GET_SUCCESS(20019, HttpStatus.OK, "신청자에 해당하는 신청 내역 조회 성공"),
    COMPLETED_MOIM_LIST_BY_GUEST_GET_SUCCESS(20020, HttpStatus.OK, "게스트에 해당하는 참가한 모임 리스트 조회 성공"),
    SUBMITTER_LIST_BY_MOIM_GET_SUCCESS(20021, HttpStatus.OK, "모임에 해당하는 신청자 전체 조회 성공"),
    MOIM_SUBMITTER_APPROVE_SUCCESS(20022, HttpStatus.OK, "모임 신청자 승인 성공"),
    MOIM_LIST_BY_HOST(20023, HttpStatus.OK, "호스트에 해당하는 모임 조회 성공"),
    SUBMITTER_LIST_GET_SUCCESS(20024, HttpStatus.OK, "호스트 승인 신청 내역 조회 성공"),
    HOST_SUBMITTER_APPROVE_SUCCESS(20025, HttpStatus.OK, "호스트 신청자 승인 성공"),
    NOTICE_DELETE_SUCCESS(20026, HttpStatus.OK, "공지사항 삭제 성공"),
    COMMENT_POST_SUCCESS(20027, HttpStatus.OK, "공지사항 댓글 작성 성공"),
    REVIEW_TAG_LIST_GET_SUCCESS(20028, HttpStatus.OK, "리뷰 태그 전체 조회 성공"),
    HOSTINTRO_GET_SUCCESS(20029, HttpStatus.OK, "호스트 소개 조회 성공"),
    MOIM_SUBMISSION_STATE_UPDATE_SUCCESS(20030, HttpStatus.OK, "모임 신청 내역 승인대기로 변경 성공"),
    NOTICE_DETAIL_GET_SUCCESS(20031, HttpStatus.OK, "공지사항 상세 조회 성공"),
    COMMENT_LIST_BY_NOTICE_GET_SUCCESS(20032, HttpStatus.OK, "공지사항에 해당하는 댓글 전체 조회 성공"),
    MOIM_SUBMISSION_ALL_GET_SUCCESS(20033, HttpStatus.OK, "모임 참여 신청 전체 조회 성공"),
    GUEST_PROFILE_UPDATE_SUCCESS(20034, HttpStatus.OK, "게스트 프로필 수정 성공"),

    // 201 Created
    MOIM_CREATE_SUCCESS(20100, HttpStatus.CREATED, "모임 개설 성공"),
    REVIEW_CREATE_SUCCESS(20101, HttpStatus.CREATED, "리뷰 생성 성공");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
