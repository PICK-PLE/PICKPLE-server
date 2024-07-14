package com.pickple.server.api.host.controller;

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

@Tag(name = "Host", description = "Host 관련 API")
public interface HostControllerDocs {

    @Operation(summary = "호스트 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20007", description = "호스트 정보 조회 성공"),
                    @ApiResponse(responseCode = "40405", description = "존재하지 않는 호스트입니다.")
            }
    )
    ApiResponseDto getHost(
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @HostId Long hostId
    );

    @Operation(summary = "모임에 해당하는 호스트 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20017", description = "모임에 해당하는 호스트 정보 조회 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다."),
                    @ApiResponse(responseCode = "40405", description = "존재하지 않는 호스트입니다.")
            }
    )
    ApiResponseDto getMoimHost(
            @PathVariable Long hostId
    );
}
