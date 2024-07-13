package com.pickple.server.api.submitter.controller;

import com.pickple.server.api.submitter.dto.request.SubmitterCreateRequest;
import com.pickple.server.global.common.annotation.GuestId;
import com.pickple.server.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
            @GuestId final Long guestId,
            @RequestBody SubmitterCreateRequest submitterCreateRequest
    );
}
