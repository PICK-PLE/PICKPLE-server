package com.pickple.server.api.user.repository;

import com.pickple.server.api.user.domain.SocialType;
import com.pickple.server.api.user.domain.User;
import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findBySocialTypeAndSocialId(final Long socialId, final SocialType socialType);
}
