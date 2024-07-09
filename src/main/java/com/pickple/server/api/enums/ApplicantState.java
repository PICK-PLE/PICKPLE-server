package com.pickple.server.api.enums;

public enum ApplicantState {
    PENDING("pending"),
    APPROVE("approve"),
    REJECT("reject");

    public String applicantState;

    ApplicantState(String applicantState){
        this.applicantState = applicantState;
    }
}
