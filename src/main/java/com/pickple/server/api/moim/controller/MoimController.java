package com.pickple.server.api.moim.controller;

import com.pickple.server.api.moim.domain.enums.Category;
import com.pickple.server.api.moim.dto.response.MoimDetailResponse;
import com.pickple.server.api.moim.dto.response.SubmittedMoimResponse;
import com.pickple.server.api.moim.service.MoimQueryService;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MoimController {

    private final MoimQueryService moimQueryService;

    @GetMapping("/v1/moim/categories")
    public ApiResponseDto<List<String>> getAllCategories() {
        return ApiResponseDto.success(SuccessCode.ALL_CATEGORY_GET_SUCCESS, Category.getCategories());
    }

    @GetMapping("/v1/moim/{moimId}")
    public ApiResponseDto<MoimDetailResponse> getMoimDetail(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.MOIM_DETAIL_GET_SUCCESS, moimQueryService.getMoimDetail(moimId));
    }

    @GetMapping("/v1/submitted-moim/{moimId}")
    public ApiResponseDto<SubmittedMoimResponse> getSubmittedMoimDetail(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.SUBMITTED_MOIM_DETAIL_GET_SUCCESS,
                moimQueryService.getSubmittedMoimDetail(moimId));
    }
}
