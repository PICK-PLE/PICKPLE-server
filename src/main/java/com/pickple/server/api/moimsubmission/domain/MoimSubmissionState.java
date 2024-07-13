package com.pickple.server.api.moimsubmission.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MoimSubmissionState {

    ALL("all"), //전체

    PENDING_PAYMENT("pendingPayment"),  //입금 대기

    PENDING_APPROVAL("pendingApproval"),    //승인 대기

    APPROVED("approved"),   //승인

    REJECTED("rejected"),   //거절

    REFUNDED("refunded"),   //환불

    COMPLETED("completed"); //완료

    public final String moimSubmissionState;
}
