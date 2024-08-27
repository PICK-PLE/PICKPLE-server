package com.pickple.server.api.review.controller;

import com.pickple.server.api.review.Service.ReviewQueryService;
import com.pickple.server.api.review.dto.TagGetResponse;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/v2/review/tag-list")
    public ApiResponseDto<TagGetResponse> getAllTags() {
        return ApiResponseDto.success(SuccessCode.REVIEW_TAG_LIST_GET_SUCCESS, reviewQueryService.getAllTags());
    }
}
