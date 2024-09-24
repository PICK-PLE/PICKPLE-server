package com.pickple.server.api.user.service;

import static com.pickple.server.global.auth.jwt.provider.JwtValidationType.EXPIRED_JWT_TOKEN;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.api.user.domain.Role;
import com.pickple.server.api.user.domain.SocialType;
import com.pickple.server.api.user.domain.User;
import com.pickple.server.api.user.dto.AccessTokenGetSuccess;
import com.pickple.server.api.user.dto.LoginSuccessResponse;
import com.pickple.server.api.user.dto.TokenDto;
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
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;
    private final KakaoSocialService kakaoSocialService;
    private final GuestRepository guestRepository;  // 의존성 분리하기
    private final HostRepository hostRepository;    // 의존성 분리하기

    public LoginSuccessResponse create(
            final String authorizationCode,
            final UserLoginRequest loginRequest
    ) {
        User user = getUser(getUserInfoResponse(authorizationCode, loginRequest));
        Guest guest = getOrCreateGuest(user);
        TokenDto tokenDto = getTokenDto(user);

        if (isExistingHost(user.getId())) {
            Host host = hostRepository.findHostByUserId(user.getId());
            return LoginSuccessResponse.of(guest.getNickname(), guest.getId(),
                    host.getNickname(), host.getId(), tokenDto);
        } else {
            return LoginSuccessResponse.of(guest.getNickname(), guest.getId(), null, null, tokenDto);
        }
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

    public User createUser(final UserInfoResponse userResponse) {
        User user = User.of(
                userResponse.socialId(),
                userResponse.email(),
                userResponse.socialType(),
                userResponse.socialNickname(),
                Role.GUEST.getRole()
        );
        return userRepository.save(user);
    }

    public Guest createGuest(final User user) {
        Guest guest = Guest.builder()
                .user(user)
                .nickname(user.getSocialNickname() + "#" + user.getId())
                .imageUrl("https://pickple-bucket.s3.ap-northeast-2.amazonaws.com/profile/guestProfileImage.png")
                .build();
        return guestRepository.save(guest);
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
        if (jwtTokenProvider.validateToken(refreshToken) == EXPIRED_JWT_TOKEN) {
            // 리프레시 토큰이 만료된 경우
            throw new CustomException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }

        Long userId = jwtTokenProvider.getUserFromJwt(refreshToken);
        if (!userId.equals(tokenService.findIdByRefreshToken(refreshToken))) {
            throw new CustomException(ErrorCode.TOKEN_INCORRECT_ERROR);
        }

        // 사용자 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 사용자 역할을 기반으로 권한 설정
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        UserAuthentication userAuthentication = new UserAuthentication(userId.toString(), null, authorities);

        return AccessTokenGetSuccess.of(
                jwtTokenProvider.issueAccessToken(userAuthentication)
        );
    }

    public TokenDto getTokenByUserId(
            final Long id
    ) {
        // 사용자 정보 가져오기
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 사용자 역할을 기반으로 권한 설정
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        UserAuthentication userAuthentication = new UserAuthentication(id.toString(), null, authorities);

        String refreshToken = jwtTokenProvider.issueRefreshToken(userAuthentication);
        tokenService.saveRefreshToken(id, refreshToken);
        return TokenDto.of(
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

    private TokenDto getTokenDto(
            final User user
    ) {
        return getTokenByUserId(user.getId());
    }

    private User getUser(final UserInfoResponse userResponse) {
        if (isExistingUser(userResponse.socialId(), userResponse.socialType())) {
            return getBySocialId(userResponse.socialId(), userResponse.socialType());
        } else {
            return createUser(userResponse);
        }
    }

    private boolean isExistingUser(
            final Long socialId,
            final SocialType socialType
    ) {
        return userRepository.findBySocialTypeAndSocialId(socialId, socialType).isPresent();
    }

    private boolean isExistingHost(final Long userId) {
        return hostRepository.existsByUserId(userId);
    }

    private Guest getOrCreateGuest(final User user) {
        return guestRepository.findByUserId(user.getId())
                .orElseGet(() -> createGuest(user));
    }
}
