package com.pickple.server.api.moim.dto.response;

import com.pickple.server.api.moim.domain.DateInfo;
import lombok.Builder;

@Builder
public record MoimByCategoryResponse(

        Long moimId,

        int dayOfDay,

        String title,

        String hostNickName,

        DateInfo dateList,

        String moimImageUrl,

        String hostImageUrl
) {
}
