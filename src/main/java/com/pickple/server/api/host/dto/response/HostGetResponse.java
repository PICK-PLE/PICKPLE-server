package com.pickple.server.api.host.dto.response;

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

    private Long hostId;
}
