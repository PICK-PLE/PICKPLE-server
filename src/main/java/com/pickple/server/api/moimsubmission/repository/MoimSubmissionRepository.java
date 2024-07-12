package com.pickple.server.api.moimsubmission.repository;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import com.pickple.server.api.moimsubmission.domain.MoimSubmissionState;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoimSubmissionRepository extends JpaRepository<MoimSubmission, Long> {
    boolean existsByMoimAndMoimSubmissionState(Moim moim, MoimSubmissionState moimSubmissionState);

    List<MoimSubmission> findAllByGuestId(Long guestId);

    List<MoimSubmission> findAllByMoimSubmissionState(MoimSubmissionState moimSubmissionState);

    MoimSubmission findBymoimIdAndGuestId(Long moimId, Long guestId);

    @Query("SELECT ms FROM MoimSubmission ms WHERE ms.guestId = :guestId AND ms.moimSubmissionState = 'COMPLETED'")
    List<MoimSubmission> findCompletedMoimSubmissionsByGuest(@Param("guestId") Long guestId);
}
