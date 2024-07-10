package com.pickple.server.api.submitter.domain;

public enum SubmitterState {

    PENDING("pending"),
    APPROVE("approve"),
    REJECT("reject");

    public String submittertState;

    SubmitterState(String submittertState) {
        this.submittertState = submittertState;
    }
}
