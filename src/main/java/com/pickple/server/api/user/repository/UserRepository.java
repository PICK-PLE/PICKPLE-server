package com.pickple.server.api.user.repository;


import com.pickple.server.api.user.domain.SocialType;
import com.pickple.server.api.user.domain.User;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import feign.Param;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    @Query("SELECT u FROM User u WHERE u.socialId = :socialId AND u.socialType = :socialType")
    Optional<User> findBySocialTypeAndSocialId(@Param("socialId") Long socialId,
                                               @Param("socialType") SocialType socialType);

    Optional<User> findUserById(Long id);

    default User findUserByIdOrThrow(Long id) {
        return findUserById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
