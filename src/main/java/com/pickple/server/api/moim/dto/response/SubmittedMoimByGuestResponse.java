package com.pickple.server.api.moim.dto.response;

import com.pickple.server.api.moim.domain.DateInfo;
import com.pickple.server.api.moimsubmission.domain.MoimSubmissionState;
import lombok.Builder;

@Builder
public record SubmittedMoimByGuestResponse(
        Long moimId,
        MoimSubmissionState moimSubmissionState,
        String title,
        String hostNickname,
        DateInfo dateList,
        int fee,
        String imageUrl
) {
}
