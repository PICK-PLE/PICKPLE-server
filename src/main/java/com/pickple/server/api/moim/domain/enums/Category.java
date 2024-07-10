package com.pickple.server.api.moim.domain.enums;

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
    public String category;

    Category(String category) {
        this.category = category;
    }
}
