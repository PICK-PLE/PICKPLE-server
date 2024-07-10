package com.pickple.server.api.submitter.repository;

import com.pickple.server.api.submitter.domain.Submitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmitterRepository extends JpaRepository<Submitter, Long> {
}