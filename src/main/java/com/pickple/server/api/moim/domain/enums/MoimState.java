package com.pickple.server.api.moim.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MoimState {

    ONGOING("ongoing"), //진행중

    COMPLETED("completed"); //완료

    public final String moimState;

}
