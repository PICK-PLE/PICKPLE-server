package com.pickple.server.api.notice.controller;

import com.pickple.server.api.notice.dto.request.NoticePostRequest;
import com.pickple.server.api.notice.service.NoticeCommandService;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeCommandService noticeCommandService;

    @PostMapping("/v1/moim/{moimId}/notice")
    public ApiResponseDto getHost(@PathVariable Long moimId,
                                  @RequestBody NoticePostRequest noticePostRequest) {
        noticeCommandService.postNotice(moimId, noticePostRequest);
        return ApiResponseDto.success(SuccessCode.HOST_POST_SUCCESS);
    }
}
