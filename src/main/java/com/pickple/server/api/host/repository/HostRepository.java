package com.pickple.server.api.host.repository;

import com.pickple.server.api.host.domain.Host;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {

    Host findHostByUserId(Long userId);

    Optional<Host> findHostById(Long id);

    default Host findHostByIdOrThrow(Long id) {
        return findHostById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.HOST_NOT_FOUND));
    }

    boolean existsByNickname(String nickname);
}
