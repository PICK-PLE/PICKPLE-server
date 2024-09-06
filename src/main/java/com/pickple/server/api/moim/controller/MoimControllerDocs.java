package com.pickple.server.api.moim.controller;

import com.pickple.server.api.moim.dto.request.MoimCreateRequest;
import com.pickple.server.api.moim.dto.response.MoimDetailResponse;
import com.pickple.server.api.moim.dto.response.MoimGetResponse;
import com.pickple.server.api.moimsubmission.dto.response.MoimByGuestResponse;
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
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Moim", description = "Moim 관련 API")
public interface MoimControllerDocs {

    @Operation(summary = "모임 개설")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20100", description = "모임 개설 성공"),
                    @ApiResponse(responseCode = "40405", description = "존재하지 않는 호스트입니다.")
            }
    )
    ApiResponseDto createMoim(
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @HostId final Long hostId,
            @RequestBody MoimCreateRequest moimCreateRequest
    );

    @Operation(summary = "카테고리 전체 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20004", description = "카테고리 전체 조회 성공")
            }
    )
    ApiResponseDto<List<String>> getAllCategories(
    );

    @Operation(summary = "모임 상세 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20006", description = "모임 상세 정보 조회 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다.")
            }
    )
    ApiResponseDto<MoimDetailResponse> getMoimDetail(
            @PathVariable Long moimId
    );

    @Operation(summary = "신청한 모임 상세 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20010", description = "신청한 모임 상세 정보 조회 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다.")
            }
    )
    ApiResponseDto<MoimByGuestResponse> getSubmittedMoimDetail(
            @PathVariable Long moimId
    );

    @Operation(summary = "카테고리에 해당하는 모임 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20012", description = "카테고리에 해당하는 모임 조회 성공")
            }
    )
    ApiResponseDto getMoimListByCategory(
            @RequestParam String category
    );

    @Operation(summary = "모임에 해당하는 소개 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20014", description = "모임에 해당하는 소개 조회 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다.")
            }
    )
    ApiResponseDto getMoimDescription(
            @PathVariable Long moimId
    );

    @Operation(summary = "모임 질문 목록 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20015", description = "모임 질문 목록 조회 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다.")
            }
    )
    ApiResponseDto getMoimQuestionList(
            @PathVariable Long moimId
    );

    @Operation(summary = "홈 배너 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20016", description = "홈 배너 조회 성공")
            }
    )
    ApiResponseDto getMoimBanner();

    @Operation(summary = "호스트와 모임상태에 해당하는 모임 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20023", description = "호스트와 모임상태에 해당하는 모임 조회 성공"),
                    @ApiResponse(responseCode = "40408", description = "호스트와 상태에 해당하는 모임이 없습니다.")
            }
    )
    ApiResponseDto getMoimListByHostAndMoimState(
            @PathVariable Long hostId,
            @RequestParam String moimState
    );


    @Operation(summary = "리뷰 작성 시 모임 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20039", description = "리뷰 작성 시 모임 정보 조회 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다.")
            }
    )
    ApiResponseDto<MoimGetResponse> getMoimForReview(
            @PathVariable Long moimId
    );

    @Operation(summary = "호스트에 해당하는 모임 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20040", description = "호스트에 해당하는 모임 조회 성공"),
                    @ApiResponse(responseCode = "40408", description = "호스트에 해당하는 모임이 없습니다.")
            }
    )
    ApiResponseDto getMoimListByHost(
            @PathVariable Long hostId
    );
}
