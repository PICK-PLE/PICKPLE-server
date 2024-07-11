package com.pickple.server.api.moim.dto.response;

import com.pickple.server.api.moim.domain.DateInfo;
import lombok.Builder;

@Builder
public record SubmittedMoimResponse(
        String title,
        String hostNickname,
        boolean isOffline,
        String spot,
        DateInfo dateList,
        int fee,
        String hostImageUrl,
        String moimImageUrl
) {
}
