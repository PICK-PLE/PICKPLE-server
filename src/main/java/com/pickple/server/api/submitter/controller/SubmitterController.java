package com.pickple.server.api.submitter.controller;

import com.pickple.server.api.submitter.dto.request.SubmitterCreateRequest;
import com.pickple.server.api.submitter.dto.response.SubmitterListGetResponse;
import com.pickple.server.api.submitter.service.SubmitterCommandService;
import com.pickple.server.api.submitter.service.SubmitterQueryService;
import com.pickple.server.global.common.annotation.GuestId;
import com.pickple.server.global.common.annotation.UserId;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubmitterController implements SubmitterControllerDocs {

    private final SubmitterCommandService submitterCommandService;
    private final SubmitterQueryService submitterQueryService;

    @PostMapping("/v1/submitter")
    public ApiResponseDto postSubmitter(@GuestId final Long guestId,
                                        @RequestBody @Valid SubmitterCreateRequest submitterCreateRequest) {
        submitterCommandService.createSubmitter(guestId, submitterCreateRequest);
        return ApiResponseDto.success(SuccessCode.SUBMITTER_POST_SUCCESS);
    }

    @GetMapping("/v1/submitter-list")
    public ApiResponseDto<List<SubmitterListGetResponse>> getSubmitterList(@UserId final Long userId) {
        return ApiResponseDto.success(SuccessCode.SUBMITTER_LIST_GET_SUCCESS,
                submitterQueryService.getSubmitterList());
    }

    @PatchMapping("/v1/submitter/{submitterId}")
    public ApiResponseDto approveSubmitter(@PathVariable("submitterId") final Long submitterId,
                                           @UserId final Long userId) {
        submitterCommandService.approveSubmitter(submitterId);
        return ApiResponseDto.success(SuccessCode.HOST_SUBMITTER_APPROVE_SUCCESS);
    }
}
