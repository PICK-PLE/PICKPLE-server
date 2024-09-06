package com.pickple.server.api.moim.dto.response;

import lombok.Builder;

@Builder
public record MoimListByHostAndMoimStateGetResponse(

        Long moimId,            //모임 id

        String title,          //모임 제목

        Long approvedGuest,     //승인된 인원

        int maxGuest,          //최대 참가인원

        String moimImage       //모임 대표 이미지
) {
}
