package com.pickple.server.api.review.repository;

import com.pickple.server.api.review.domain.Review;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findReviewById(Long id);

    default Review findReviewByIdOrThrow(Long id) {
        return findReviewById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));
    }

    boolean existsByMoimIdAndGuestId(Long moimId, Long guestId);

    List<Review> findReviewsByMoimIdOrderByCreatedAt(Long moimId);

}