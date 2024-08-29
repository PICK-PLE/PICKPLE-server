package com.pickple.server.api.host.dto.response;

import lombok.Builder;

@Builder
public record HostByMoimResponse(
        String hostNickName,    // 호스트 닉네임
        String hostImageUrl,    // 호스트 프로필 사진 url
        int count,           // 호스트의 모임 횟수(두자릿수)
        String keyword,
        String description
) {
}
