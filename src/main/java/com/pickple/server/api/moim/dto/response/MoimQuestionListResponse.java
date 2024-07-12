package com.pickple.server.api.moim.dto.response;

import com.pickple.server.api.moim.domain.QuestionInfo;
import lombok.Builder;

@Builder
public record MoimQuestionListResponse(
        QuestionInfo questionList
) {
}
