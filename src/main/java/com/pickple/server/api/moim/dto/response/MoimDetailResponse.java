package com.pickple.server.api.moim.dto.response;

import com.pickple.server.api.moim.domain.DateInfo;
import com.pickple.server.api.moim.domain.ImageInfo;
import lombok.Builder;

@Builder
public record MoimDetailResponse(
        int dayOfDay,
        String title,
        DateInfo dateList,
        boolean isOffline,
        String spot,
        int maxGuest,
        int fee,
        ImageInfo imageList,
        Long hostId
) {
}
