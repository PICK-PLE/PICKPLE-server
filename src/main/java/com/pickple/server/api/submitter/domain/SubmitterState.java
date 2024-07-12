package com.pickple.server.api.submitter.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubmitterState {

    PENDING("pending"),

    APPROVE("approve"),

    REJECT("reject");

    public final String submitterState;
}
