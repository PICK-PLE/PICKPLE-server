package com.pickple.server.global.auth.client.service;

import com.pickple.server.global.auth.client.dto.UserInfoResponse;
import com.pickple.server.global.auth.client.dto.UserLoginRequest;

public interface SocialService {
    UserInfoResponse login(final String authorizationToken, final UserLoginRequest loginRequest);
}
