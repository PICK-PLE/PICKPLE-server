package com.pickple.server.api.host.controller;

import com.pickple.server.api.host.dto.request.HostUpdateRequest;
import com.pickple.server.api.host.dto.response.HostByMoimResponse;
import com.pickple.server.api.host.dto.response.HostGetResponse;
import com.pickple.server.api.host.dto.response.HostIntroGetResponse;
import com.pickple.server.api.host.service.HostCommandService;
import com.pickple.server.api.host.service.HostQueryService;
import com.pickple.server.global.common.annotation.HostId;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HostController implements HostControllerDocs {

    private final HostQueryService hostQueryService;
    private final HostCommandService hostCommandService;

    @GetMapping("/v2/host")
    public ApiResponseDto<HostGetResponse> getHost(@HostId final Long hostId) {
        return ApiResponseDto.success(SuccessCode.HOST_DETAIL_GET_SUCCESS, hostQueryService.getHost(hostId));
    }

    @GetMapping("/v2/host/{hostId}")
    public ApiResponseDto<HostByMoimResponse> getMoimHost(@PathVariable Long hostId) {
        return ApiResponseDto.success(SuccessCode.HOST_BY_MOIM_GET_SUCCESS, hostQueryService.getHostByMoim(hostId));
    }


    @GetMapping("/v2/host/{hostId}/intro")
    public ApiResponseDto<HostIntroGetResponse> getHostIntro(@PathVariable Long hostId) {
        return ApiResponseDto.success(SuccessCode.HOSTINTRO_GET_SUCCESS, hostQueryService.getHostIntro(hostId));
    }

    @PatchMapping("v2/host/{hostId}")
    public ApiResponseDto<HostUpdateRequest> updateHostProfile(@PathVariable final Long hostId,
                                                               @RequestBody @Valid HostUpdateRequest hostUpdateRequest) {
        hostCommandService.updateHostProfile(hostId, hostUpdateRequest);
        return ApiResponseDto.success(SuccessCode.HOST_PROFILE_UPDATE_SUCCESS);
    }
}
