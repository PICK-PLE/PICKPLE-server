package com.pickple.server.api.moim.domain.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Category {

    NJOB("N잡"),
    INVESTMENT("재테크"),
    STARTUP("창업"),
    EMPLOYMENT("취업,이직"),
    PRODUCTIVITY("생산성"),
    LIFESTYLE("라이프스타일"),
    HEALTH("건강"),
    MIND("마인드"),
    HOBBY("취미"),
    LANGUAGE("외국어");
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
