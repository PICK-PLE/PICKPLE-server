package com.pickple.server.api.moim.domain.enums;

public enum MoimState {
    PENDING_PAYMENT("pendinigPayment"),
    PENDING_APPROVAL("pendingApproval"),
    APPROVED("approved"),
    REJECTED("rejected"),
    REFUNDED("refunded"),
    COMPLETED("completed")
    ;

    public String moimState;

    MoimState(String moimState){
        this.moimState = moimState;
    }
}
