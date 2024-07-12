package com.pickple.server.api.notice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NoticePostRequest(
        @Size(max = 25, message = "제목은 최대 25자 이내로 작성해주세요.")
        @NotBlank(message = "제목 비어 있습니다.")
        String noticeTitle,//공지사항 제목

        @Size(max = 500, message = "내용은 최대 500자 이내로 작성해주세요.")
        @NotBlank(message = "내용이 비어 있습니다.")
        String noticeContent,    //공지사항 내용

        String imageUrl    //공지사항 이미지
) {

}
