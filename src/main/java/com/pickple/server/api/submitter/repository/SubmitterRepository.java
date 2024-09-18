package com.pickple.server.api.submitter.repository;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.submitter.domain.Submitter;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmitterRepository extends JpaRepository<Submitter, Long> {

    boolean existsByGuestAndSubmitterState(Guest guest, String submitterState);

    Optional<Submitter> findSubmitterById(Long id);

    default Submitter findSubmitterByIdOrThrow(Long id) {
        return findSubmitterById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.SUBMITTER_NOT_FOUND));
    }

    boolean existsByNicknameAndSubmitterState(String nickname, String submitterState);

    Submitter findSubmitterByGuestId(Long guestId);
}