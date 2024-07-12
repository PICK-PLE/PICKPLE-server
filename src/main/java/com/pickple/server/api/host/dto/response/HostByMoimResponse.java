package com.pickple.server.api.host.dto.response;

import com.pickple.server.api.host.domain.HostCategoryInfo;
import lombok.Builder;

@Builder
public record HostByMoimResponse(
        String hostNickName,    // 호스트 닉네임
        String hostImageUrl,    // 호스트 프로필 사진 url
        String count,           // 호스트의 모임 횟수(두자릿수)
        HostCategoryInfo hostCategoryList  // 호스트가 선택한 카테고리 리스트
) {
}
