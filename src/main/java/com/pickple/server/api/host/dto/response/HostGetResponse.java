package com.pickple.server.api.host.dto.response;

import lombok.Builder;

@Builder
public record HostGetResponse(

        String hostNickName,    // 호스트 닉네임

        String hostImageUrl, // 호스트 이미지

        String hostLink, // 호스트가 추가한 링크

        Long hostId,

        String keyword, //키워드

        int moimCount, // 모임 수

        int attendeeCount, // 참여자 수

        boolean isVeteran
) {
}
