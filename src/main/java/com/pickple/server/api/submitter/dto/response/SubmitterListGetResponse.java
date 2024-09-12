package com.pickple.server.api.submitter.dto.response;

import lombok.Builder;

@Builder
public record SubmitterListGetResponse(
        String guestNickname,
        Long guestId,

        Long submitterId,

        String intro,

        String goal,

        String link,

        String nickname,

        String userKeyword,

        String plan,

        String email,

        String submitterState
) {
}
