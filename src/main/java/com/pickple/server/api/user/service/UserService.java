package com.pickple.server.api.user.service;

import com.pickple.server.api.user.domain.SocialType;
import com.pickple.server.api.user.domain.User;
import com.pickple.server.api.user.dto.AccessTokenGetSuccess;
import com.pickple.server.api.user.dto.LoginSuccessResponse;
import com.pickple.server.api.user.repository.UserRepository;
import com.pickple.server.global.auth.client.dto.UserInfoResponse;
import com.pickple.server.global.auth.client.dto.UserLoginRequest;
import com.pickple.server.global.auth.client.kakao.KakaoSocialService;
import com.pickple.server.global.auth.jwt.provider.JwtTokenProvider;
import com.pickple.server.global.auth.jwt.service.TokenService;
import com.pickple.server.global.auth.security.UserAuthentication;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;
    private final KakaoSocialService kakaoSocialService;

    public LoginSuccessResponse create(
            final String authorizationCode,
            final UserLoginRequest loginRequest
    ) {
        return getTokenDto(getUserInfoResponse(authorizationCode, loginRequest));
    }

    public UserInfoResponse getUserInfoResponse(
            final String authorizationCode,
            final UserLoginRequest loginRequest
    ) {
        switch (loginRequest.socialType()) {
            case KAKAO:
                return kakaoSocialService.login(authorizationCode, loginRequest);
            default:
                throw new CustomException(ErrorCode.SOCIAL_TYPE_BAD_REQUEST);
        }
    }

    public Long createUser(final UserInfoResponse userResponse) {
        User user = User.of(
                userResponse.socialId(),
                userResponse.email(),
                userResponse.socialType(),
                userResponse.socialNickname()
        );
        return userRepository.save(user).getId();
    }

    public User getBySocialId(
            final Long socialId,
            final SocialType socialType
    ) {
        User user = userRepository.findBySocialTypeAndSocialId(socialId, socialType).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        return user;
    }

    public AccessTokenGetSuccess refreshToken(
            final String refreshToken
    ) {
        Long userId = jwtTokenProvider.getUserFromJwt(refreshToken);
        if (!userId.equals(tokenService.findIdByRefreshToken(refreshToken))) {
            throw new CustomException(ErrorCode.TOKEN_INCORRECT_ERROR);
        }
        UserAuthentication userAuthentication = new UserAuthentication(userId, null, null);
        return AccessTokenGetSuccess.of(
                jwtTokenProvider.issueAccessToken(userAuthentication)
        );
    }

    public boolean isExistingUser(
            final Long socialId,
            final SocialType socialType
    ) {
        return userRepository.findBySocialTypeAndSocialId(socialId, socialType).isPresent();
    }

    public LoginSuccessResponse getTokenByUserId(
            final Long id
    ) {
        UserAuthentication userAuthentication = new UserAuthentication(id, null, null);
        String refreshToken = jwtTokenProvider.issueRefreshToken(userAuthentication);
        tokenService.saveRefreshToken(id, refreshToken);
        return LoginSuccessResponse.of(
                jwtTokenProvider.issueAccessToken(userAuthentication),
                refreshToken
        );
    }

    @Transactional
    public void deleteUser(
            final Long id
    ) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.USER_NOT_FOUND)
                );
        userRepository.delete(user);
    }

    private LoginSuccessResponse getTokenDto(
            final UserInfoResponse userResponse
    ) {
        if (isExistingUser(userResponse.socialId(), userResponse.socialType())) {
            return getTokenByUserId(getBySocialId(userResponse.socialId(), userResponse.socialType()).getId());
        } else {
            Long id = createUser(userResponse);
            return getTokenByUserId(id);
        }
    }
}
