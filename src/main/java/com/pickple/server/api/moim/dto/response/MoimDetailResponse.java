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
    public record DateList(
            String date,
            String dayOfWeek,
            String startTime,
            String endTime
    ) {
    }

    public record ImageList(
            String imageUrl1,
            String imageUrl2,
            String imageUrl3
    ) {
    }

    public static MoimDetailResponse of(
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
        return MoimDetailResponse.builder()
                .dayOfDay(dayOfDay)
                .title(title)
                .dateList(dateList)
                .isOffline(isOffline)
                .spot(spot)
                .maxGuest(maxGuest)
                .fee(fee)
                .imageList(imageList)
                .hostId(hostId)
                .build();
    }
}
