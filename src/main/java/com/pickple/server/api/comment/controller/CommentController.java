package com.pickple.server.api.comment.controller;

import com.pickple.server.api.comment.dto.request.CommentCreateRequest;
import com.pickple.server.api.comment.service.CommentCommandService;
import com.pickple.server.global.common.annotation.HostId;
import com.pickple.server.global.common.annotation.UserId;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/v2/notice/{noticeId}/comment")
    public ApiResponseDto createComment(@UserId Long guestId,
                                        @HostId Long hostId,
                                        @PathVariable Long noticeId,
                                        @Valid @RequestBody CommentCreateRequest commentCreateRequest) {
        commentCommandService.createComment(guestId, hostId, noticeId, commentCreateRequest);
        return ApiResponseDto.success(SuccessCode.COMMENT_POST_SUCCESS);
    }
}
