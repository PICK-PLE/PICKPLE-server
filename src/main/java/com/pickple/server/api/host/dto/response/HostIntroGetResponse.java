package com.pickple.server.api.host.dto.response;

import lombok.Builder;

@Builder
public record HostIntroGetResponse(

        String nickName,    // 호스트 닉네임

        String profileUrl,    // 호스트 프로필 사진 url

        String keyword,

        String description,

        String socialLink,

        boolean isVeteran
) {
}