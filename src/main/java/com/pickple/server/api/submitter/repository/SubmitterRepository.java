package com.pickple.server.api.submitter.repository;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.submitter.domain.Submitter;
import com.pickple.server.api.submitter.domain.SubmitterState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmitterRepository extends JpaRepository<Submitter, Long> {
    boolean existsByGuestAndSubmitterState(Guest guest, SubmitterState submitterState);
}