package com.pickple.server.api.review.dto.request;

import com.pickple.server.global.common.annotation.MinSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;

@Builder
public record ReviewCreateReqeust(
        
        @NotNull
        @MinSize(value = 2, message = "태그는 최소 2개 이상 선택해야 합니다.")
        List<String> tagList,

        @NotBlank(message = "리뷰 내용이 비어있습니다.")
        String content,

        String imageUrl
) {
}