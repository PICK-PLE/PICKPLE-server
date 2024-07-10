package com.pickple.server.api.submitter.domain;

public enum SubmitterState {

    PENDING("pending"),
    APPROVE("approve"),
    REJECT("reject");

    public final String submitterState;

    SubmitterState(String submitterState) {
        this.submitterState = submitterState;
    }
}
