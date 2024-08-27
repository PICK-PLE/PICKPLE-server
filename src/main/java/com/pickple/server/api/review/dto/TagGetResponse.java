package com.pickple.server.api.review.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record TagGetResponse(
        List moimTag,
        List hostTag
) {
}
