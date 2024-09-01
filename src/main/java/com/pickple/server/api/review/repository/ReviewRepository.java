package com.pickple.server.api.review.repository;

import com.pickple.server.api.review.domain.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByMoimIdAndGuestId(Long moimId, Long guestId);

    @Query("SELECT r FROM Review r WHERE r.moim.id = :moimId")
    List<Review> findReviewListByMoimId(Long moimId);

    List<Review> findReviewByMoimId(Long moimId);
}