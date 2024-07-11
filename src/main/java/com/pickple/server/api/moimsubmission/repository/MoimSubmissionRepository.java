package com.pickple.server.api.moimsubmission.repository;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import com.pickple.server.api.moimsubmission.domain.MoimSubmissionState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoimSubmissionRepository extends JpaRepository<MoimSubmission, Long> {
    boolean existsByMoimAndMoimSubmissionState(Moim moim, MoimSubmissionState moimSubmissionState);
}
