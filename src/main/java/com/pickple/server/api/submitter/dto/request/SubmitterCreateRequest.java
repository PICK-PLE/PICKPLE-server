package com.pickple.server.api.submitter.dto.request;

import com.pickple.server.api.submitter.domain.SubmitterCategoryInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public record SubmitterCreateRequest(
        @Size(max = 300, message = "소개글은 최대 300자 이내로 작성해주세요.")
        @NotBlank(message = "소개글이 비어 있습니다.")
        String intro,      //관리자에게 보여질 intro

        @Size(max = 300, message = "목표는 최대 300자 이내로 작성해주세요.")
        @NotBlank(message = "목표가 비어 있습니다.")
        String goal,        //이루고 싶은 목표

        @NotBlank(message = "링크가 비어 있습니다.")
        String link,    //호스트의 sns 및 홈페이지 링크

        @Size(max = 10, message = "닉네임은 최대 15자 이내로 작성해주세요.")
        @NotBlank(message = "닉네임이 비어 있습니다.")
        String nickname,    //호스트 승인 후 사용될 닉네임

        @Valid
        SubmitterCategoryInfo categoryList,    //호스트 카테고리 리스트

        @Size(max = 300, message = "글모임명은 최대 300자 이내로 작성해주세요.")
        @NotBlank(message = "계획이 비어 있습니다.")
        String plan,    //클래스 모임 운영 계획

        @NotBlank(message = "메일이 비어 있습니다.")
        @Pattern(message = "이메일 형식이 올바르지 않습니다.", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
        String email,    //호스트 승인 후 연락받을 메일 주소

        @NotBlank(message = "직업이 비어 있습니다.")
        String job
) {
}
