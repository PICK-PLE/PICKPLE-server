package com.pickple.server.api.moim.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    NJOB("njob"),

    INVESTMENT("investment"),

    STARTUP("startup"),

    EMPLOYMENT("employment"),

    PRODUCTIVITY("productivity"),

    LIFESTYLE("lifestyle"),

    HEALTH("health"),

    MIND("mind"),

    HOBBY("hobby"),

    LANGUAGE("language");

    public final String category;
}
