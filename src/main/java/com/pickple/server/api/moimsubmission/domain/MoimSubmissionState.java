package com.pickple.server.api.moimsubmission.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MoimSubmissionState {

    ALL("all"),

    PENDING_PAYMENT("pendingPayment"),

    PENDING_APPROVAL("pendingApproval"),

    APPROVED("approved"),

    REJECTED("rejected"),

    REFUNDED("refunded"),

    COMPLETED("completed");

    public final String moimSubmissionState;
}
