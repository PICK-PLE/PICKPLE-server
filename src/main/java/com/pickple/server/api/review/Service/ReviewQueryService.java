package com.pickple.server.api.review.Service;

import com.pickple.server.api.review.domain.Review;
import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.review.domain.enums.HostTag;
import com.pickple.server.api.review.domain.enums.MoimTag;
import com.pickple.server.api.review.dto.response.ReviewListGetByHostResponse;
import com.pickple.server.api.review.dto.response.TagListGetResponse;
import com.pickple.server.api.review.repository.ReviewRepository;
import com.pickple.server.global.util.DateTimeUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final MoimRepository moimRepository;
    private final ReviewRepository reviewRepository;

    public TagListGetResponse getAllTags() {
        return TagListGetResponse.builder()
                .moimTag(Arrays.stream(MoimTag.values())
                        .map(MoimTag::getDescription)
                        .collect(Collectors.toList()))
                .hostTag(Arrays.stream(HostTag.values())
                        .map(HostTag::getDescription)
                        .collect(Collectors.toList()))
                .build();
    }

    public List<ReviewListGetByMoimResponse> getReviewListByMoim(Long moimId) {
        List<Review> reviews = reviewRepository.findReviewListByMoimId(moimId);

        return reviews.stream()
                .map(review -> ReviewListGetByMoimResponse.builder()
                        .tagList(review.getTagList())
                        .content(review.getContent())
                        .reviewImageUrl(review.getImageUrl())
                        .guestNickname(review.getGuest().getNickname())
                        .guestImageUrl(review.getGuest().getImageUrl())
                        .date(DateTimeUtil.refineDateAndTime(review.getCreatedAt()))
                        .build())
                .collect(Collectors.toList());
  }

    public List<ReviewListGetByHostResponse> getReviewListByHost(Long hostId) {
        List<Moim> moimList = moimRepository.findMoimByHostId(hostId);

        return moimList.stream()
                .flatMap(oneMoim -> reviewRepository.findReviewByMoimId(oneMoim.getId()).stream()
                        .map(review -> ReviewListGetByHostResponse.builder()
                                .moimId(oneMoim.getId())
                                .moimTitle(oneMoim.getTitle())
                                .tagList(review.getTagList())
                                .content(review.getContent())
                                .reviewImageUrl(review.getImageUrl())
                                .guestNickname(review.getGuest().getNickname())
                                .guestImageUrl(review.getGuest()
                                        .getImageUrl())
                                .date(DateTimeUtil.refineDateAndTime(review.getCreatedAt()))
                                .build()))
                .collect(Collectors.toList());
    }
}
