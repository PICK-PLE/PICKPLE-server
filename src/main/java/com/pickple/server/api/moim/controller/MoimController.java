package com.pickple.server.api.moim.controller;

import com.pickple.server.api.moim.domain.QuestionInfo;
import com.pickple.server.api.moim.dto.request.MoimCreateRequest;
import com.pickple.server.api.moim.dto.response.MoimByCategoryResponse;
import com.pickple.server.api.moim.dto.response.MoimCreateResponse;
import com.pickple.server.api.moim.dto.response.MoimDescriptionResponse;
import com.pickple.server.api.moim.dto.response.MoimDetailResponse;
import com.pickple.server.api.moim.dto.response.MoimGetResponse;
import com.pickple.server.api.moim.dto.response.MoimListByHostAndMoimStateGetResponse;
import com.pickple.server.api.moim.dto.response.MoimListByHostGetResponse;
import com.pickple.server.api.moim.service.MoimCommandService;
import com.pickple.server.api.moim.service.MoimQueryService;
import com.pickple.server.api.moimsubmission.dto.response.MoimByGuestResponse;
import com.pickple.server.global.common.annotation.GuestId;
import com.pickple.server.global.common.annotation.HostId;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import jakarta.validation.Valid;
import java.util.List;
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
public class MoimController implements MoimControllerDocs {

    private final MoimQueryService moimQueryService;
    private final MoimCommandService moimCommandService;

    @PostMapping("/v1/moim")
    public ApiResponseDto<MoimCreateResponse> createMoim(@HostId Long hostId,
                                                         @RequestBody @Valid MoimCreateRequest moimCreateRequest) {
        return ApiResponseDto.success(SuccessCode.MOIM_CREATE_SUCCESS,
                moimCommandService.createMoim(hostId, moimCreateRequest));
    }

    @GetMapping("/v1/moim/categories")
    public ApiResponseDto<List<String>> getAllCategories() {
        return ApiResponseDto.success(SuccessCode.ALL_CATEGORY_GET_SUCCESS, moimQueryService.getCategories());
    }

    @GetMapping("/v2/moim/{moimId}")
    public ApiResponseDto<MoimDetailResponse> getMoimDetail(@PathVariable final Long moimId,
                                                            @GuestId final Long guestId) {
        return ApiResponseDto.success(SuccessCode.MOIM_DETAIL_GET_SUCCESS,
                moimQueryService.getMoimDetail(moimId, guestId));
    }

    @GetMapping("/v1/submitted-moim/{moimId}")
    public ApiResponseDto<MoimByGuestResponse> getSubmittedMoimDetail(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.SUBMITTED_MOIM_DETAIL_GET_SUCCESS,
                moimQueryService.getSubmittedMoimDetail(moimId));
    }

    @GetMapping("/v1/moim-list")
    public ApiResponseDto<List<MoimByCategoryResponse>> getMoimListByCategory(@RequestParam String category) {
        return ApiResponseDto.success(SuccessCode.MOIM_LIST_BY_CATEGORY_GET_SUCCESS,
                moimQueryService.getMoimListByCategory(category));
    }

    @GetMapping("/v1/moim/{moimId}/description")
    public ApiResponseDto<MoimDescriptionResponse> getMoimDescription(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.MOIM_DESCRIPTION_GET_SUCCESS,
                moimQueryService.getMoimDescription(moimId));
    }

    @GetMapping("/v1/moim/{moimId}/question-list")
    public ApiResponseDto<QuestionInfo> getMoimQuestionList(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.MOIM_QUESTION_LIST_GET_SUCCESS,
                moimQueryService.getMoimQuestionList(moimId));
    }

    @GetMapping("/v1/moim/banner")
    public ApiResponseDto<Long> getMoimBanner() {
        return ApiResponseDto.success(SuccessCode.MOIM_BANNER_GET_SUCCESS,
                moimQueryService.getMoimBanner());
    }

    @GetMapping("/v1/host/{hostId}/moim-list")
    public ApiResponseDto<List<MoimListByHostAndMoimStateGetResponse>> getMoimListByHostAndMoimState(
            @PathVariable Long hostId,
            @RequestParam String moimState) {
        return ApiResponseDto.success(SuccessCode.MOIM_LIST_BY_HOST_AND_MOIMSTATE,
                moimQueryService.getMoimListByHostAndMoimState(hostId, moimState));
    }

    @GetMapping("/v2/host/{hostId}/moim-list")
    public ApiResponseDto<List<MoimListByHostGetResponse>> getMoimListByHost(@PathVariable Long hostId) {
        return ApiResponseDto.success(SuccessCode.MOIM_LIST_BY_HOST,
                moimQueryService.getMoimListByHost(hostId));
    }

    @GetMapping("/v2/moim/{moimId}/review")
    public ApiResponseDto<MoimGetResponse> getMoimForReview(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.MOIM_FOR_REVIEW_GET_SUCCESS,
                moimQueryService.getMoimForReview(moimId));
    }
}
