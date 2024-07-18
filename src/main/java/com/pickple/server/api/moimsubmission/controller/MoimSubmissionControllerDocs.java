package com.pickple.server.api.moimsubmission.controller;

import com.pickple.server.api.moimsubmission.dto.request.MoimSubmitRequest;
import com.pickple.server.api.moimsubmission.dto.request.MoimSubmitterUpdateRequest;
import com.pickple.server.global.common.annotation.GuestId;
import com.pickple.server.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "MoimSubmission", description = "MoimSubmission 관련 API")
public interface MoimSubmissionControllerDocs {
    @Operation(summary = "모임 참여")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20008", description = "모임 참여 신청 성공"),
                    @ApiResponse(responseCode = "40007", description = "이미 대기중인 모임입니다."),
                    @ApiResponse(responseCode = "40403", description = "존재하지 않는 게스트입니다."),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다.")
            }
    )
    ApiResponseDto createMoimSubmission(
            @PathVariable Long moimId,
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @GuestId final Long guestId,
            @RequestBody MoimSubmitRequest moimSubmitRequest
    );

    @Operation(summary = "게스트에 해당하는 신청한 모임 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20018", description = "게스트에 해당하는 신청한 모임 리스트 조회 성공"),
                    @ApiResponse(responseCode = "40403", description = "존재하지 않는 게스트입니다."),
            }
    )
    ApiResponseDto getSubmittedMoimListByGuest(
            @PathVariable Long guestId,
            @RequestParam String moimSubmissionState
    );

    @Operation(summary = "신청자에 해당하는 신청 내역 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20019", description = "신청자에 해당하는 신청 내역 조회 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다."),
                    @ApiResponse(responseCode = "40406", description = "해당 모임에 신청한 내역이 없습니다.")
            }
    )
    ApiResponseDto getSubmissionDetail(
            @PathVariable Long moimId, @PathVariable Long submitterId
    );

    @Operation(summary = "게스트에 해당하는 참가한 모임 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20020", description = "게스트에 해당하는 참가한 모임 리스트 조회 성공"),
                    @ApiResponse(responseCode = "40403", description = "존재하지 않는 게스트입니다.")
            }
    )
    ApiResponseDto getCompletedMoimListByGuest(
            @PathVariable Long guestId
    );

    @Operation(summary = "모임에 해당하는 신청자 전체 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20021", description = "모임에 해당하는 신청자 전체 조회 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다.")
            }
    )
    ApiResponseDto getSubmitterListByMoim(
            @PathVariable Long moimId
    );

    @Operation(summary = "신청자 승인")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20022", description = "신청자 승인 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다.")
            }
    )
    ApiResponseDto updateSubmitterState(
            @PathVariable Long moimId,
            @RequestBody MoimSubmitterUpdateRequest moimSubmitterUpdateRequest
    );
}
