package com.pickple.server.api.submitter.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class SubmitterCategoryInfo {

    @NotBlank(message = "카테고리가 비었습니다.")
    private final String category1;
    private final String category2;
    private final String category3;

    public static SubmitterCategoryInfo of(String category1, String category2, String category3) {
        return SubmitterCategoryInfo.builder()
                .category1(category1)
                .category2(category2)
                .category3(category3)
                .build();
    }
}
