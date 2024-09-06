package com.pickple.server.api.review.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ReviewListGetByHostResponse(

        Long moimId,    //moimId

        String moimTitle,    //moim 제목

        List tagList,    //

        String content,    //리뷰 내용

        String reviewImageUrl,    //리뷰 이미지

        String guestNickname,    //리뷰 작성자(게스트) 닉네임

        String guestImageUrl,    //리뷰 작성자(게스트) 프로필 이미지

        String date    //리뷰 작성 일자
) {
}
