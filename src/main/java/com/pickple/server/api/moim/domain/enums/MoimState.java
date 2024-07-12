package com.pickple.server.api.moim.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MoimState {

    ONGOING("ongoing"),

    COMPLETED("completed");

    public final String moimState;
}
