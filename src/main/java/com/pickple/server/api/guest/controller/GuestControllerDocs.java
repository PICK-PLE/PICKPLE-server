package com.pickple.server.api.guest.controller;

import com.pickple.server.api.guest.dto.request.GuestUpdateRequest;
import com.pickple.server.global.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Guest", description = "Guest 관련 API")
public interface GuestControllerDocs {

    @Operation(summary = "게스트 프로필 수정")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20034", description = "게스트 프로필 수정 성공"),
                    @ApiResponse(responseCode = "40008", description = "이미 존재하는 닉네임입니다."),
                    @ApiResponse(responseCode = "40403", description = "존재하지 않는 게스트입니다.")
            }
    )
    ApiResponseDto updateGuestProfile(
            final Long guestId,
            final GuestUpdateRequest guestUpdateRequest
    );

    @Operation(summary = "게스트 프로필 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20041", description = "게스트 프로필 조회 성공"),
                    @ApiResponse(responseCode = "40403", description = "존재하지 않는 게스트입니다.")
            }
    )
    ApiResponseDto getGuest(
            final Long guestId
    );
}
