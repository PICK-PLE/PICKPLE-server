package com.pickple.server.global.auth.client.dto;

import com.pickple.server.api.user.domain.SocialType;

public record UserInfoResponse(
        Long socialId,
        SocialType socialType,
        String email,
        String socialNickname
) {
    public static UserInfoResponse of(
            final Long socialId,
            final SocialType socialType,
            final String email,
            final String socialNickname
    ) {
        return new UserInfoResponse(socialId, socialType, email, socialNickname);
    }
}
