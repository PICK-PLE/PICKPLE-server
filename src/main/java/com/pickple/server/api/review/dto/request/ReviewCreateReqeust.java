package com.pickple.server.api.review.dto.request;

import java.util.List;
import lombok.Builder;

@Builder
public record ReviewCreateReqeust(
        List<String> tagList,
        String content,
        String imageUrl
) {
}