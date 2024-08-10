package com.pickple.server.api.moimsubmission.dto.request;

import com.pickple.server.api.moimsubmission.domain.AccountInfo;
import com.pickple.server.api.moimsubmission.domain.AnswerInfo;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public record MoimSubmitRequest(
        @Valid
        AnswerInfo answerList,
        AccountInfo accountList
) {
}
