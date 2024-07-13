package com.pickple.server.api.moimsubmission.controller;

import com.pickple.server.api.moimsubmission.dto.request.MoimSubmitRequest;
import com.pickple.server.api.moimsubmission.service.MoimSubmissionCommandService;
import com.pickple.server.api.moimsubmission.service.MoimSubmissionQueryService;
import com.pickple.server.global.common.annotation.GuestId;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MoimSubmissionController implements MoimSubmissionControllerDocs {

    private final MoimSubmissionCommandService moimSubmissionCommandService;
    private final MoimSubmissionQueryService moimSubmissionQueryService;

    @PostMapping("/v1/moim/{moimId}")
    public ApiResponseDto createMoimSubmission(
            @PathVariable Long moimId,
            @GuestId Long guestId,
            @RequestBody MoimSubmitRequest moimSubmitRequest
    ) {
        moimSubmissionCommandService.createMoimSubmission(moimId, guestId, moimSubmitRequest);
        return ApiResponseDto.success(SuccessCode.MOIM_SUBMISSION_POST_SUCCESS);
    }

    @GetMapping("/v1/guest/{guestId}/submitted-moim-list")
    public ApiResponseDto getSubmittedMoimListByGuest(
            @PathVariable Long guestId,
            @RequestParam String moimSubmissionState
    ) {
        return ApiResponseDto.success(SuccessCode.SUBMITTED_MOIM_LIST_BY_GUEST_GET_SUCCESS,
                moimSubmissionQueryService.getSubmittedMoimListByGuest(guestId, moimSubmissionState));
    }

    @GetMapping("/v1/moim/{moimId}/submitter/{submitterId}")
    public ApiResponseDto getSubmissionDetail(
            @PathVariable Long moimId, @PathVariable Long submitterId
    ) {
        return ApiResponseDto.success(SuccessCode.SUBMISSION_DETAIL_GET_SUCCESS,
                moimSubmissionQueryService.getSubmittionDetail(moimId, submitterId));
    }

    @GetMapping("/v1/guest/{guestId}/completed-moim-list")
    public ApiResponseDto getCompletedMoimListByGuest(
            @PathVariable Long guestId
    ) {
        return ApiResponseDto.success(SuccessCode.COMPLETED_MOIM_LIST_BY_GUEST_GET_SUCCESS,
                moimSubmissionQueryService.getCompletedMoimListByGuest(guestId));
    }
}
