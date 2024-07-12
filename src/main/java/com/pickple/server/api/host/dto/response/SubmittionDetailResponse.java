package com.pickple.server.api.host.dto.response;

import com.pickple.server.api.moim.domain.QuestionInfo;
import com.pickple.server.api.moimsubmission.domain.AnswerInfo;
import lombok.Builder;

@Builder
public record SubmittionDetailResponse(
        QuestionInfo questionList,
        AnswerInfo answerList
) {
}
