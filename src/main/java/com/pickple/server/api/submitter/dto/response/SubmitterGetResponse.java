package com.pickple.server.api.submitter.dto.response;

import com.pickple.server.api.submitter.domain.SubmitterCategoryInfo;
import lombok.Builder;

@Builder
public record SubmitterGetResponse(
        String guestNickname,
        Long guestId,

        Long submitterId,

        String intro,

        String goal,

        String link,

        String nickname,

        SubmitterCategoryInfo categoryList,

        String plan,

        String email,

        String submitterState
) {
}
