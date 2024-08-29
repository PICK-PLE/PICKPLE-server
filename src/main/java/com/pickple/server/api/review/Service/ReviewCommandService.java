package com.pickple.server.api.review.Service;

import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.review.domain.Review;
import com.pickple.server.api.review.dto.request.ReviewCreateReqeust;
import com.pickple.server.api.review.repository.ReviewRepository;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandService {
    private final GuestRepository guestRepository;
    private final MoimRepository moimRepository;
    private final ReviewRepository reviewRepository;

    public void createReview(Long moimId, Long guestId, ReviewCreateReqeust reviewCreateReqeust) {
        if (reviewRepository.existsByMoimIdAndGuestId(moimId, guestId)) {
            throw new CustomException(ErrorCode.DUPLICATION_REVIEW);
        }
        Review review = Review.builder()
                .guest(guestRepository.findGuestByIdOrThrow(guestId))
                .moim(moimRepository.findMoimByIdOrThrow(moimId))
                .tagList(reviewCreateReqeust.tagList())
                .content(reviewCreateReqeust.content())
                .imageUrl(reviewCreateReqeust.imageUrl())
                .build();

        reviewRepository.save(review);
    }
}