package com.pickple.server.api.submitter.domain;

public enum SubmitterState {

    PENDING("pending"),
    APPROVE("approve"),
    REJECT("reject");

    public String applicantState;

    SubmitterState(String applicantState) {
        this.applicantState = applicantState;
    }
}
