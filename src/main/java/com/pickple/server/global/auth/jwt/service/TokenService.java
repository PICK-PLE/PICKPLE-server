package com.pickple.server.global.auth.jwt.service;


import com.pickple.server.global.auth.jwt.repository.TokenRepository;
import com.pickple.server.global.auth.redis.domain.Token;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Transactional
    public void saveRefreshToken(final Long userId, final String refreshToken) {
        tokenRepository.save(
                Token.of(userId, refreshToken)
        );
    }

    public Long findIdByRefreshToken(final String refreshToken) {
        Token token = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.REFRESH_TOKEN_NOT_FOUND)
                );
        return token.getId();
    }

    @Transactional
    public void deleteRefreshToken(final Long userId) {
        Token token = tokenRepository.findById(userId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.REFRESH_TOKEN_NOT_FOUND)
                );
        tokenRepository.delete(token);
    }
}