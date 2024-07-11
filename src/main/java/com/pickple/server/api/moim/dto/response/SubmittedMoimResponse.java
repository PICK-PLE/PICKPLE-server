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
    public record DateList(
            String date,
            String dayOfWeek,
            String startTime,
            String endTime
    ) {
    }

    public static SubmittedMoimResponse of(
            String title,
            String hostNickname,
            boolean isOffline,
            String spot,
            DateInfo dateList,
            int fee,
            String hostImageUrl,
            String moimImageUrl
    ) {
        return SubmittedMoimResponse.builder()
                .title(title)
                .hostNickname(hostNickname)
                .isOffline(isOffline)
                .spot(spot)
                .dateList(dateList)
                .fee(fee)
                .hostImageUrl(hostImageUrl)
                .moimImageUrl(moimImageUrl)
                .build();
    }
}
