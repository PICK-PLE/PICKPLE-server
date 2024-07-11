package com.pickple.server.api.moim.repository;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoimRepository extends JpaRepository<Moim, Long> {
    Optional<Moim> findMoimById(Long id);

    default Moim findMoimByIdOrThrow(Long id) {
        return findMoimById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MOIM_NOT_FOUND));
    }
}
