package com.pickple.server.api.submitter.controller;

import com.pickple.server.api.submitter.dto.request.SubmitterCreateRequest;
import com.pickple.server.api.submitter.dto.response.SubmitterListGetResponse;
import com.pickple.server.global.common.annotation.GuestId;
import com.pickple.server.global.common.annotation.UserId;
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

@Tag(name = "Submitter", description = "Submitter 관련 API")
public interface SubmitterControllerDocs {
    @Operation(summary = "호스트 승인 신청")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20005", description = "호스트 승인 신청 성공"),
                    @ApiResponse(responseCode = "40003", description = "대기중인 호스트 승인 신청이 있습니다."),
                    @ApiResponse(responseCode = "40403", description = "존재하지 않는 게스트입니다.")
            }
    )
    ApiResponseDto postSubmitter(
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @GuestId final Long guestId,
            @RequestBody SubmitterCreateRequest submitterCreateRequest
    );

    @Operation(summary = "호스트 승인 신청 내역 전체 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20024", description = "호스트 승인 신청 내역 조회 성공")
            }
    )
    ApiResponseDto<List<SubmitterListGetResponse>> getSubmitterList(
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @UserId final Long userId
    );

    @Operation(summary = "호스트 신청 승인")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20025", description = "호스트 신청자 승인 성공"),
                    @ApiResponse(responseCode = "40104", description = "관리자 계정이 아닙니다."),
                    @ApiResponse(responseCode = "40408", description = "호스트 승인 신청이 존재하지 않습니다.")
            }
    )
    ApiResponseDto approveSubmitter(
            @PathVariable("submitterId") final Long submitterId,
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @UserId final Long userId
    );
}
