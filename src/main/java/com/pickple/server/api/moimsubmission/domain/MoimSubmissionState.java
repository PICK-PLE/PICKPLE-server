package com.pickple.server.api.moimsubmission.domain;

public enum MoimSubmissionState {

    PENDING_PAYMENT("pendinigPayment"),
    PENDING_APPROVAL("pendingApproval"),
    APPROVED("approved"),
    REJECTED("rejected"),
    REFUNDED("refunded"),
    COMPLETED("completed");

    public String moimSubmissionState;

    MoimSubmissionState(String moimSubmissionState) {
        this.moimSubmissionState = moimSubmissionState;
    }
}
