package com.pickple.server.api.review.controller;

import com.pickple.server.api.review.dto.response.TagListGetResponse;
import com.pickple.server.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review", description = "Review 관련 API")
public interface ReviewControllerDocs {

    @Operation(summary = "리뷰 태그 전체 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20027", description = "리뷰 태그 전체 조회 성공")
            }
    )
    ApiResponseDto<TagListGetResponse> getAllTags(
    );
}
