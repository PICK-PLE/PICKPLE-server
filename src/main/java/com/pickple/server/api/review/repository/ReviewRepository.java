package com.pickple.server.api.review.repository;

import com.pickple.server.api.review.domain.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByMoimIdAndGuestId(Long moimId, Long guestId);

    List<Review> findReviewByMoimId(Long moimId);
}