package com.pickple.server.api.moim.controller;

import com.pickple.server.api.moim.domain.enums.Category;
import com.pickple.server.api.moim.dto.request.MoimCreateRequest;
import com.pickple.server.api.moim.dto.response.MoimDetailResponse;
import com.pickple.server.api.moim.service.MoimCommandService;
import com.pickple.server.api.moim.service.MoimQueryService;
import com.pickple.server.api.moimsubmission.dto.response.MoimByGuestResponse;
import com.pickple.server.global.common.annotation.HostId;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
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
    public ApiResponseDto createMoim(@HostId Long hostId, @RequestBody MoimCreateRequest moimCreateRequest) {
        moimCommandService.createMoim(hostId, moimCreateRequest);
        return ApiResponseDto.success(SuccessCode.MOIM_CREATE_SUCCESS);
    }

    @GetMapping("/v1/moim/categories")
    public ApiResponseDto<List<String>> getAllCategories() {
        return ApiResponseDto.success(SuccessCode.ALL_CATEGORY_GET_SUCCESS, Category.getCategories());
    }

    @GetMapping("/v1/moim/{moimId}")
    public ApiResponseDto<MoimDetailResponse> getMoimDetail(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.MOIM_DETAIL_GET_SUCCESS, moimQueryService.getMoimDetail(moimId));
    }

    @GetMapping("/v1/submitted-moim/{moimId}")
    public ApiResponseDto<MoimByGuestResponse> getSubmittedMoimDetail(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.SUBMITTED_MOIM_DETAIL_GET_SUCCESS,
                moimQueryService.getSubmittedMoimDetail(moimId));
    }

    @GetMapping("/v1/moim-list")
    public ApiResponseDto getMoimListByCategory(@RequestParam String category) {
        return ApiResponseDto.success(SuccessCode.MOIM_LIST_BY_CATEGORY_GET_SUCCESS,
                moimQueryService.getMoimListByCategory(category));
    }

    @GetMapping("/v1/moim/{moimId}/description")
    public ApiResponseDto getMoimDescription(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.MOIM_DESCRIPTION_GET_SUCCESS,
                moimQueryService.getMoimDescription(moimId));
    }

    @GetMapping("/v1/moim/{moimId}/question-list")
    public ApiResponseDto getMoimQuestionList(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.MOIM_QUESTION_LIST_GET_SUCCESS,
                moimQueryService.getMoimQuestionList(moimId));
    }

    @GetMapping("/v1/moim/banner")
    public ApiResponseDto getMoimBanner() {
        return ApiResponseDto.success(SuccessCode.MOIM_BANNER_GET_SUCCESS,
                moimQueryService.getMoimBanner());
    }

    @GetMapping("/v1/host/{hostId}/moim-list")
    public ApiResponseDto getMoimListByHostId(@PathVariable Long hostId, @RequestParam String moimState) {
        return ApiResponseDto.success(SuccessCode.MOIM_LIST_BY_HOST,
                moimQueryService.getMoimListByHost(hostId, moimState));
    }
}
