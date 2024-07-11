package com.pickple.server.api.moim.domain.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    Category(String category) {
        this.category = category;
    }

    public static List<String> getCategories() {
        return Arrays.stream(Category.values())
                .map(category -> category.category)
                .collect(Collectors.toList());
    }
}
