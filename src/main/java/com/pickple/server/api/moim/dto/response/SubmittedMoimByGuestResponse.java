package com.pickple.server.api.moim.dto.response;

import com.pickple.server.api.moim.domain.DateInfo;
import lombok.Builder;

@Builder
public record SubmittedMoimByGuestResponse(
        Long moimId,
        String moimSubmissionState,
        String title,
        String hostNickname,
        DateInfo dateList,
        int fee,
        String imageUrl,
        boolean isReviewed
) {
}
