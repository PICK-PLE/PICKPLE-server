package com.pickple.server.global.auth.client.kakao;

import com.pickple.server.api.user.domain.SocialType;
import com.pickple.server.global.auth.client.dto.UserInfoResponse;
import com.pickple.server.global.auth.client.dto.UserLoginRequest;
import com.pickple.server.global.auth.client.kakao.response.KakaoAccessTokenResponse;
import com.pickple.server.global.auth.client.kakao.response.KakaoUserResponse;
import com.pickple.server.global.auth.client.service.SocialService;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoSocialService implements SocialService {

    private static final String AUTH_CODE = "authorization_code";
    private static final String REDIRECT_URI = "https://pick-ple.com/kakao/redirection";

    @Value("${kakao.clientId}")
    private String clientId;
    private final KakaoApiClient kakaoApiClient;
    private final KakaoAuthApiClient kakaoAuthApiClient;


    @Transactional
    @Override
    public UserInfoResponse login(
            final String authorizationCode,
            final UserLoginRequest loginRequest
    ) {
        String accessToken;
        try {
            // 인가 코드로 Access Token + Refresh Token 받아오기
            accessToken = getOAuth2Authentication(authorizationCode);
        } catch (FeignException e) {
            throw new CustomException(ErrorCode.AUTHENTICATION_CODE_EXPIRED);
        }
        // Access Token으로 유저 정보 불러오기
        return getLoginDto(loginRequest.socialType(), getUserInfo(accessToken));
    }

    private String getOAuth2Authentication(
            final String authorizationCode
    ) {
        KakaoAccessTokenResponse response = kakaoAuthApiClient.getOAuth2AccessToken(
                AUTH_CODE,
                clientId,
                REDIRECT_URI,
                authorizationCode
        );
        return response.accessToken();
    }

    private KakaoUserResponse getUserInfo(
            final String accessToken
    ) {
        return kakaoApiClient.getUserInformation("Bearer " + accessToken);
    }

    private UserInfoResponse getLoginDto(
            final SocialType socialType,
            final KakaoUserResponse userResponse
    ) {
        return UserInfoResponse.of(
                userResponse.id(),
                socialType,
                userResponse.kakaoAccount().email(),
                userResponse.kakaoAccount().profile().nickname()
        );
    }

}
