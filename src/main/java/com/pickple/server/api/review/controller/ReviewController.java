package com.pickple.server.api.review.controller;

import com.pickple.server.api.review.Service.ReviewCommandService;
import com.pickple.server.api.review.Service.ReviewQueryService;
import com.pickple.server.api.review.dto.request.ReviewCreateReqeust;
import com.pickple.server.api.review.dto.response.ReviewListGetByHostResponse;
import com.pickple.server.api.review.dto.response.TagListGetResponse;
import com.pickple.server.global.common.annotation.GuestId;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @GetMapping("/v2/review/tag-list")
    public ApiResponseDto<TagListGetResponse> getAllTags() {
        return ApiResponseDto.success(SuccessCode.REVIEW_TAG_LIST_GET_SUCCESS, reviewQueryService.getAllTags());
    }

    @PostMapping("/v2/moim/{moimId}/review")
    public ApiResponseDto createReview(
            @PathVariable("moimId") final Long moimId,
            @GuestId final Long guestId,
            @RequestBody @Valid final ReviewCreateReqeust reviewCreateRequest
    ) {
        reviewCommandService.createReview(moimId, guestId, reviewCreateRequest);
        return ApiResponseDto.success(SuccessCode.REVIEW_CREATE_SUCCESS);
    }

    @GetMapping("/v2/host/{hostId}/review-list")
    public ApiResponseDto<List<ReviewListGetByHostResponse>> getReviewListByHost(
            @PathVariable("hostId") final Long hostId
    ) {
        return ApiResponseDto.success(SuccessCode.REVIEW_LIST_BY_HOST_GET_SUCCESS,
                reviewQueryService.getReviewListByHost(hostId));
    }
}
