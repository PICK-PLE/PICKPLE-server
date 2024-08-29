package com.pickple.server.api.review.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record TagListGetResponse(
        List<String> moimTag,
        List<String> hostTag
) {
}
