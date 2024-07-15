package com.pickple.server.api.host.controller;

import com.pickple.server.api.host.dto.response.HostByMoimResponse;
import com.pickple.server.api.host.dto.response.HostGetResponse;
import com.pickple.server.api.host.service.HostQueryService;
import com.pickple.server.global.common.annotation.HostId;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HostController implements HostControllerDocs {

    private final HostQueryService hostQueryService;

    @GetMapping("/v1/host")
    public ApiResponseDto<HostGetResponse> getHost(@HostId Long hostId) {
        return ApiResponseDto.success(SuccessCode.HOST_DETAIL_GET_SUCCESS, hostQueryService.getHost(hostId));
    }

    @GetMapping("/v1/host/{hostId}")
    public ApiResponseDto<HostByMoimResponse> getMoimHost(@PathVariable Long hostId) {
        return ApiResponseDto.success(SuccessCode.HOST_BY_MOIM_GET_SUCCESS, hostQueryService.getHostByMoim(hostId));
    }
}
