package com.pickple.server.api.comment.controller;

import com.pickple.server.api.comment.dto.request.CommentCreateRequest;
import com.pickple.server.api.comment.dto.response.CommentGetResponse;
import com.pickple.server.api.comment.service.CommentCommandService;
import com.pickple.server.api.comment.service.CommentQueryService;
import com.pickple.server.global.common.annotation.UserId;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController implements CommentControllerDocs {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @PostMapping("/v2/notice/{noticeId}/comment")
    public ApiResponseDto createComment(@UserId Long userId,
                                        @PathVariable Long noticeId,
                                        @Valid @RequestBody CommentCreateRequest commentCreateRequest) {
        commentCommandService.createComment(userId, noticeId, commentCreateRequest);
        return ApiResponseDto.success(SuccessCode.COMMENT_POST_SUCCESS);
    }

    @GetMapping("/v2/notice/{noticeId}/comment-list")
    public ApiResponseDto<List<CommentGetResponse>> getCommentListByNotice(@PathVariable Long noticeId) {
        return ApiResponseDto.success(SuccessCode.COMMENT_LIST_BY_NOTICE_GET_SUCCESS,
                commentQueryService.getCommentListByNotice(noticeId));
    }

    @DeleteMapping("/v2/notice/{noticeId}/comment/{commentId}")
    public ApiResponseDto deleteComment(@UserId Long userId,
                                        @PathVariable final Long noticeId,
                                        @PathVariable final Long commentId) {
        commentCommandService.deleteComment(userId, noticeId, commentId);
        return ApiResponseDto.success(SuccessCode.COMMENT_DELETE_SUCCESS);
    }
}
