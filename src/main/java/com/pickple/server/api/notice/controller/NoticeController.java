package com.pickple.server.api.notice.controller;

import com.pickple.server.api.notice.dto.request.NoticeCreateRequest;
import com.pickple.server.api.notice.dto.response.NoticeDetailGetResponse;
import com.pickple.server.api.notice.dto.response.NoticeListGetByMoimResponse;
import com.pickple.server.api.notice.service.NoticeCommandService;
import com.pickple.server.api.notice.service.NoticeQueryService;
import com.pickple.server.global.common.annotation.HostId;
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
public class NoticeController implements NoticeControllerDocs {

    private final NoticeCommandService noticeCommandService;
    private final NoticeQueryService noticeQueryService;

    @PostMapping("/v2/moim/{moimId}/notice")
    public ApiResponseDto createNotice(@PathVariable Long moimId,
                                       @RequestBody @Valid NoticeCreateRequest noticeCreateRequest) {
        noticeCommandService.createNotice(moimId, noticeCreateRequest);
        return ApiResponseDto.success(SuccessCode.NOTICE_POST_SUCCESS);
    }

    @GetMapping("/v1/moim/{moimId}/notice-list")
    public ApiResponseDto<List<NoticeListGetByMoimResponse>> getNoticeListByMoimId(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.NOTICE_LIST_GET_SUCCESS,
                noticeQueryService.getNoticeListByMoimId(moimId));
    }

    @DeleteMapping("/v2/notice/{noticeId}")
    public ApiResponseDto deleteNotice(@HostId Long hostId,
                                       @PathVariable Long noticeId) {
        noticeCommandService.deleteNotice(hostId, noticeId);
        return ApiResponseDto.success(SuccessCode.NOTICE_DELETE_SUCCESS);
    }

    @GetMapping("/v2/moim/{moimId}/notice/{noticeId}")
    public ApiResponseDto<NoticeDetailGetResponse> getNoticeDetail(@HostId Long userId,
                                                                   @PathVariable Long moimId,
                                                                   @PathVariable Long noticeId) {
        return ApiResponseDto.success(SuccessCode.NOTICE_DETAIL_GET_SUCCESS,
                noticeQueryService.getNoticeDetail(userId, moimId, noticeId));
    }
}
