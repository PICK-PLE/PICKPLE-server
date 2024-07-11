package com.pickple.server.api.moimsubmission.dto.request;

import com.pickple.server.api.moimsubmission.domain.AccountInfo;
import com.pickple.server.api.moimsubmission.domain.AnswerInfo;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MoimSubmitRequest(
        AnswerInfo answerList,
        AccountInfo accountList
) {
    public record AnswerList(
            @Size(max = 200, message = "답변은 200자 이내로 작성해주세요.")
            @NotBlank(message = "답변이 비어있습니다.")
            String answer1,

            @Size(max = 200, message = "답변은 200자 이내로 작성해주세요.")
            @NotBlank(message = "답변이 비어있습니다.")
            @Nullable
            String answer2,

            @Size(max = 200, message = "답변은 200자 이내로 작성해주세요.")
            @NotBlank(message = "답변이 비어있습니다.")
            @Nullable
            String answer3
    ) {
    }

    public record AccountList(
            @NotBlank(message = "예금주가 비어있습니다.")
            String holder,

            @NotBlank(message = "입금 은행이 비어있습니다.")
            String bank,

            @Size(max = 20, message = "계좌번호는 20자 이내로 작성해주세요.")
            @NotBlank(message = "계좌번호가 비어있습니다.")
            String accountNumber
    ) {
    }
}
