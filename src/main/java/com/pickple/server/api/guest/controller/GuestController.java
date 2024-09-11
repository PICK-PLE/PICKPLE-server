package com.pickple.server.api.guest.controller;

import com.pickple.server.api.guest.dto.request.GuestUpdateRequest;
import com.pickple.server.api.guest.service.GuestCommandService;
import com.pickple.server.api.guest.service.GuestQueryService;
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
public class GuestController implements GuestControllerDocs {

    private final GuestCommandService guestCommandService;
    private final GuestQueryService guestQueryService;

    @PatchMapping("/v2/guest/{guestId}")
    public ApiResponseDto updateGuestProfile(@PathVariable final Long guestId,
                                             @RequestBody @Valid final GuestUpdateRequest guestUpdateRequest) {
        guestCommandService.updateGuestNickname(guestId, guestUpdateRequest);
        return ApiResponseDto.success(SuccessCode.GUEST_PROFILE_UPDATE_SUCCESS);
    }

    @GetMapping("/v2/guest/{guestId}")
    public ApiResponseDto getGuest(@PathVariable final Long guestId) {
        return ApiResponseDto.success(SuccessCode.GUEST_GET_SUCCESS, guestQueryService.getGuest(guestId));
    }

}
