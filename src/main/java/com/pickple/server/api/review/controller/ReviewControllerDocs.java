package com.pickple.server.api.review.controller;

import com.pickple.server.api.review.dto.request.ReviewCreateReqeust;
import com.pickple.server.api.review.dto.response.ReviewListGetByHostResponse;
import com.pickple.server.api.review.dto.response.TagListGetResponse;
import com.pickple.server.global.common.annotation.GuestId;
import com.pickple.server.global.common.annotation.HostId;
import com.pickple.server.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Review", description = "Review 관련 API")
public interface ReviewControllerDocs {

    @Operation(summary = "리뷰 태그 전체 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20028", description = "리뷰 태그 전체 조회 성공")
            }
    )
    ApiResponseDto<TagListGetResponse> getAllTags(
    );

    @Operation(summary = "리뷰 생성")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20101", description = "리뷰 생성 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다."),
                    @ApiResponse(responseCode = "40403", description = "존재하지 않는 게스트입니다.")
            }
    )
    ApiResponseDto createReview(
            @PathVariable final Long moimId,
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @GuestId final Long guestId,
            @RequestBody ReviewCreateReqeust reviewCreateReqeust
    );

    @Operation(summary = "호스트에 해당하는 리뷰 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20037", description = "호스트에 해당하는 리뷰 조회 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다."),
                    @ApiResponse(responseCode = "40405", description = "존재하지 않는 호스트입니다.")
            }
    )
    ApiResponseDto<List<ReviewListGetByHostResponse>> getReviewListByHost(
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @HostId final Long hostId
    );
}
