package com.pickple.server.api.moimsubmission.repository;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoimSubmissionRepository extends JpaRepository<MoimSubmission, Long> {
    boolean existsByMoimAndGuestId(Moim moim, Long guestId);

    List<MoimSubmission> findAllByGuestId(Long guestId);

    @Query("SELECT ms FROM MoimSubmission ms WHERE ms.guestId = :guestId AND ms.moimSubmissionState = :moimSubmissionState")
    List<MoimSubmission> findSubmittedMoimSubmissionsByGuestIdAndMoimSubmissionState(Long guestId,
                                                                                     String moimSubmissionState);

    MoimSubmission findByMoimIdAndGuestId(Long moimId, Long guestId);

    List<MoimSubmission> findByMoimId(Long moimId);

    List<MoimSubmission> findMoimListByMoimId(Long moimId);

    @Query("SELECT ms FROM MoimSubmission ms WHERE ms.moim.id = :moimId AND ms.moimSubmissionState IN ('pendingApproval', 'approved', 'rejected')")
    List<MoimSubmission> findMoimListByMoimIdAndMoimSubmissionState(@Param("moimId") Long moimId);

    @Query("SELECT ms FROM MoimSubmission ms WHERE ms.guestId = :guestId AND ms.moimSubmissionState = 'completed'")
    List<MoimSubmission> findCompletedMoimSubmissionsByGuest(@Param("guestId") Long guestId);

    @Query(value = "SELECT COUNT(*) FROM moim_submissions WHERE moim_id = :moimId AND moim_submission_state = 'approved'", nativeQuery = true)
    long countApprovedMoimSubmissions(@Param("moimId") Long moimId);
}
