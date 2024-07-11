package com.pickple.server.api.moim.controller;

import com.pickple.server.api.moim.domain.enums.Category;
import com.pickple.server.api.moim.dto.response.MoimDetailResponse;
import com.pickple.server.api.moim.service.MoimCommandService;
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

    private final MoimCommandService moimCommandService;

    @GetMapping("/v1/moim/categories")
    public ApiResponseDto<List<String>> getAllCategories() {
        return ApiResponseDto.success(SuccessCode.ALL_CATEGORY_GET_SUCCESS, Category.getCategories());
    }

    @GetMapping("/v1/moim/{moimId}")
    public ApiResponseDto<MoimDetailResponse> getMoimDetail(@PathVariable Long moimId) {
        return ApiResponseDto.success(SuccessCode.MOIM_DETAIL_GET_SUCCESS, moimCommandService.getMoimDetail(moimId));
    }
}
