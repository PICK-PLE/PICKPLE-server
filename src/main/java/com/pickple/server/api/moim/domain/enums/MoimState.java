package com.pickple.server.api.moim.domain.enums;

public enum MoimState {
    ONGOING("ONGOING"),
    COMPLETED("COMPLETED");

    public final String moimState;

    MoimState(String moimState) {
        this.moimState = moimState;
    }

}
