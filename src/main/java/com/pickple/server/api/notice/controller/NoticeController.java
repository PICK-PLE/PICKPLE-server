package com.pickple.server.api.notice.controller;

import com.pickple.server.api.notice.dto.request.NoticeCreateRequest;
import com.pickple.server.api.notice.dto.response.NoticeListGetByMoimResponse;
import com.pickple.server.api.notice.service.NoticeCommandService;
import com.pickple.server.api.notice.service.NoticeQueryService;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoticeController implements NoticeControllerDocs {

    private final NoticeCommandService noticeCommandService;
    private final NoticeQueryService noticeQueryService;

    @PostMapping("/v1/moim/{moimId}/notice")
    public ApiResponseDto createNotice(@PathVariable Long moimId,
                                       @RequestBody NoticeCreateRequest noticeCreateRequest) {
        noticeCommandService.createNotice(moimId, noticeCreateRequest);
        return ApiResponseDto.success(SuccessCode.NOTICE_POST_SUCCESS);
    }

    @GetMapping("/v1/moim/{moimId}/notice-list")
    public ApiResponseDto<List<NoticeListGetByMoimResponse>> getNoticeListByMoimId(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.NOTICE_LIST_GET_SUCCESS,
                noticeQueryService.getNoticeListByMoimId(moimId));
    }
}
