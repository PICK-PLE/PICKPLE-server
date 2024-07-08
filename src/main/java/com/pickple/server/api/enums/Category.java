package com.pickple.server.api.enums;

public enum Category {
    NJOB("N잡"),
    INVESTMENT("재테크"),
    STARTUP("창업"),
    EMPLOYMENT("취업, 이직"),
    PRODUCTIVITY("생산성"),
    LIFESTYLE("라이프스타일"),
    HEALTH("건강"),
    MIND("마인드"),
    HOBBY("취미"),
    LANGUAGE("외국어");
    public String category;

    Category(String category){
        this.category = category;
    }
}
