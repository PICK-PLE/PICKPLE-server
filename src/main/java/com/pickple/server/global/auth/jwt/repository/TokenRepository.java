package com.pickple.server.global.auth.jwt.repository;

import com.pickple.server.global.auth.redis.domain.Token;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long> {

    Optional<Token> findByRefreshToken(final String refreshToken);

    Optional<Token> findById(final Long id);
}