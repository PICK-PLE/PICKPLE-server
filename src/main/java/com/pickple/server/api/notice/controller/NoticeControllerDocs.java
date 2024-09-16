package com.pickple.server.api.notice.controller;

import com.pickple.server.api.notice.dto.request.NoticeCreateRequest;
import com.pickple.server.api.notice.dto.response.NoticeDetailGetResponse;
import com.pickple.server.global.common.annotation.GuestId;
import com.pickple.server.global.common.annotation.HostId;
import com.pickple.server.global.common.annotation.UserId;
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

@Tag(name = "Notice", description = "Notice 관련 API")
public interface NoticeControllerDocs {

    @Operation(summary = "공지사항 작성")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20011", description = "공지사항 작성 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다.")
            }
    )
    ApiResponseDto createNotice(
            @PathVariable Long moimId,
            @RequestBody NoticeCreateRequest noticeCreateRequest
    );

    @Operation(summary = "모임에 해당하는 공지사항 전체 조회 ")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20013", description = "공지사항 리스트 조회 성공"),
                    @ApiResponse(responseCode = "40404", description = "존재하지 않는 모임입니다.")
            }
    )
    ApiResponseDto getNoticeListByMoimId(
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @GuestId Long guestId,
            @PathVariable Long moimId
    );

    @Operation(summary = "공지사항 삭제")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20026", description = "공지사항 삭제 성공"),
                    @ApiResponse(responseCode = "40409", description = "존재하지 않는 공지사항입니다.")
            }
    )
    ApiResponseDto deleteNotice(
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @HostId Long hostId,
            @PathVariable Long noticeId
    );

    @Operation(summary = "공지사항 상세 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20031", description = "공지사항 상세 조회 성공"),
                    @ApiResponse(responseCode = "40409", description = "존재하지 않는 공지사항입니다.")
            }
    )
    ApiResponseDto<NoticeDetailGetResponse> getNoticeDetail(
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @UserId Long userId,
            @PathVariable Long moimId,
            @PathVariable Long noticeId
    );
}
