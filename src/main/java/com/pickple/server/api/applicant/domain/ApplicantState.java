package com.pickple.server.api.applicant.domain;

public enum ApplicantState {
    PENDING("pending"),
    APPROVE("approve"),
    REJECT("reject");

    public String applicantState;

    ApplicantState(String applicantState){
        this.applicantState = applicantState;
    }
}
