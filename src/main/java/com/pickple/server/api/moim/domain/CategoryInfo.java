package com.pickple.server.api.moim.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryInfo {

    @NotBlank(message = "카테고리가 비어있습니다.")
    private String category1;

    private String category2;

    private String category3;

}
