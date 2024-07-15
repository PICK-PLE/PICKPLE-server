package com.pickple.server.api.submitter.repository;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.submitter.domain.Submitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmitterRepository extends JpaRepository<Submitter, Long> {
    boolean existsByGuestAndSubmitterState(Guest guest, String submitterState);

    boolean existsByNickname(String nickname);
}