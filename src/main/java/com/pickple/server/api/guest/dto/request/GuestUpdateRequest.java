package com.pickple.server.api.guest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GuestUpdateRequest(
        @Size(max = 15)
        @NotBlank(message = "닉네임이 비어있습니다.")
        String guestNickname
) {
}
