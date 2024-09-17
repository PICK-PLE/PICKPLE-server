package com.pickple.server.api.moim.dto.response;

import com.pickple.server.api.moim.domain.DateInfo;
import lombok.Builder;

@Builder
public record MoimGetResponse(

        String title,

        String moimImageUrl,

        String hostImageUrl,

        String hostNickName,

        DateInfo dateList
) {
}
