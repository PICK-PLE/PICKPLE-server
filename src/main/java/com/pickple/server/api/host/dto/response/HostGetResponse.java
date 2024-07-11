package com.pickple.server.api.host.dto.response;

import com.pickple.server.api.host.domain.HostCategoryInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HostGetResponse {

    private String hostNickName;    // 호스트 닉네임

    private String hostImageUrl; // 호스트 이미지

    private String hostLink; // 호스트가 추가한 링크

    private HostCategoryInfo hostCategoryList;  // 호스트가 선택한 카테고리 리스트

    private Long hostId;
}
