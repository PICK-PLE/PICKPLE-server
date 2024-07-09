package com.pickple.server.api.enums;

public enum ApplicantState {
    PENDING("PENDING"),
    APPROVE("APPROVE"),
    REJECT("REJECT");

    public String applicantState;

    ApplicantState(String applicantState){
        this.applicantState = applicantState;
    }
}
