package com.pickple.server.api.comment.controller;

import com.pickple.server.api.comment.dto.request.CommentCreateRequest;
import com.pickple.server.api.comment.dto.response.CommentGetResponse;
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

@Tag(name = "Comment", description = "Comment 관련 API")
public interface CommentControllerDocs {

    @Operation(summary = "공지사항 댓글 작성")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20027", description = "공지사항 댓글 작성 성공"),
                    @ApiResponse(responseCode = "40403", description = "존재하지 않는 게스트입니다."),
                    @ApiResponse(responseCode = "40405", description = "존재하지 않는 호스트입니다."),
                    @ApiResponse(responseCode = "40409", description = "존재하지 않는 공지사항입니다.")
            }
    )
    ApiResponseDto createComment(
            @Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH)
            @UserId Long userId,
            @PathVariable Long noticeId,
            @RequestBody CommentCreateRequest commentCreateRequest
    );

    @Operation(summary = "공지사항 댓글 전체 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "20032", description = "공지사항 댓글 작성 성공"),
                    @ApiResponse(responseCode = "40409", description = "존재하지 않는 공지사항입니다.")
            }
    )
    ApiResponseDto<List<CommentGetResponse>> getCommentListByNotice(
            @PathVariable Long noticeId
    );
}
