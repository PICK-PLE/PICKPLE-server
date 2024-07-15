package com.pickple.server.api.moimsubmission.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AnswerInfo {

    @Size(max = 200, message = "200자 이내로 작성해주세요.")
    @NotBlank(message = "답변이 비어있습니다.")
    private String answer1;

    @Size(max = 200, message = "200자 이내로 작성해주세요.")
    private String answer2;

    @Size(max = 200, message = "200자 이내로 작성해주세요.")
    private String answer3;
}
