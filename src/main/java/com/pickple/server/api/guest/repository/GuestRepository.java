package com.pickple.server.api.guest.repository;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    Optional<Guest> findGuestById(Long id);

    Guest findGuestByUserId(Long userId);

    default Guest findGuestByIdOrThrow(Long id) {
        return findGuestById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.GUEST_NOT_FOUND));
    }
}
