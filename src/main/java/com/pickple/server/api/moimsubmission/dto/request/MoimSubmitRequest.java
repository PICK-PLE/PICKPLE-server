package com.pickple.server.api.moimsubmission.dto.request;

import com.pickple.server.api.moimsubmission.domain.AccountInfo;
import com.pickple.server.api.moimsubmission.domain.AnswerInfo;

public record MoimSubmitRequest(
        AnswerInfo answerList,
        AccountInfo accountList
) {
}
