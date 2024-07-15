package com.pickple.server.api.moim.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class QuestionInfo {

    @Size(max = 50, message = "50자 이내로 작성해주세요.")
    @NotBlank(message = "질문이 비어있습니다.")
    private String question1;

    @Size(max = 50, message = "50자 이내로 작성해주세요.")
    private String question2;

    @Size(max = 50, message = "50자 이내로 작성해주세요.")
    private String question3;

}
