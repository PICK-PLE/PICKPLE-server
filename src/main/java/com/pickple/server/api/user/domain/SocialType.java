package com.pickple.server.api.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialType {
    KAKAO("KAKAO"),
    ;
    private final String type;
}
