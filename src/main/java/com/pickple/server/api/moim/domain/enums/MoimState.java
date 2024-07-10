package com.pickple.server.api.moim.domain.enums;

public enum MoimState {
    ONGOING("ONGOING"),
    COMPLETED("COMPLETED");

    public String moimState;

    MoimState(String moimState) {
        this.moimState = moimState;
    }

}
