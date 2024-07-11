package com.pickple.server.api.moimsubmission.controller;

import com.pickple.server.api.moimsubmission.dto.request.MoimSubmitRequest;
import com.pickple.server.api.moimsubmission.service.MoimSubmissionCommandService;
import com.pickple.server.global.common.annotation.GuestId;
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
public class MoimSubmissionController {

    private final MoimSubmissionCommandService moimSubmissionCommandService;

    @PostMapping("/v1/moim/{moimId}")
    public ApiResponseDto createMoimSubmission(
            @PathVariable Long moimId,
            @GuestId Long guestId,
            @RequestBody MoimSubmitRequest moimSubmitRequest
    ) {
        moimSubmissionCommandService.createMoimSubmission(moimId, guestId, moimSubmitRequest);
        return ApiResponseDto.success(SuccessCode.MOIM_SUBMISSION_POST_SUCCESS);
    }
}
