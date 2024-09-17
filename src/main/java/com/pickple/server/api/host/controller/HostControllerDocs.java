package com.pickple.server.api.host.controller;

import com.pickple.server.api.host.dto.request.HostUpdateRequest;
import com.pickple.server.api.host.dto.response.HostByMoimResponse;
import com.pickple.server.api.host.dto.response.HostGetResponse;
import com.pickple.server.api.host.dto.response.HostIntroGetResponse;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Host", description = "Host 관련 API")
public interface HostControllerDocs {

    @Operation(summary = "호스트 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20007", description = "호스트 정보 조회 성공"),
                    @ApiResponse(responseCode = "40405", description = "존재하지 않는 호스트입니다."),
                    @ApiResponse(responseCode = "40408", description = "호스트 승인 신청이 존재하지 않습니다."),
                    @ApiResponse(responseCode = "40003", description = "대기중인 호스트 승인 신청이 있습니다.")
            }
    )
    ApiResponseDto<HostGetResponse> getHost(
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @HostId Long hostId,
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @GuestId Long guestId
    );

    @Operation(summary = "모임에 해당하는 호스트 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20017", description = "모임에 해당하는 호스트 정보 조회 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다."),
                    @ApiResponse(responseCode = "40405", description = "존재하지 않는 호스트입니다.")
            }
    )
    ApiResponseDto<HostByMoimResponse> getMoimHost(
            @PathVariable Long hostId
    );

    @Operation(summary = "호스트 소개 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20029", description = "호스트 소개 조회 성공"),
                    @ApiResponse(responseCode = "40405", description = "존재하지 않는 호스트입니다.")
            }
    )
    ApiResponseDto<HostIntroGetResponse> getHostIntro(
            @PathVariable Long hostId
    );

    @Operation(summary = "호스트 프로필 수정")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20035", description = "호스트 프로필 수정 성공"),
                    @ApiResponse(responseCode = "40405", description = "존재하지 않는 호스트입니다.")
            }
    )
    ApiResponseDto<HostUpdateRequest> updateHostProfile(
            @PathVariable final Long hostId,
            @RequestBody HostUpdateRequest hostUpdateRequest
    );
}
