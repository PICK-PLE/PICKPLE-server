package com.pickple.server.api.host.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class HostCategoryInfo {

    @NotBlank(message = "카테고리가 비었습니다.")
    private final String category1;
    private final String category2;
    private final String category3;
}
