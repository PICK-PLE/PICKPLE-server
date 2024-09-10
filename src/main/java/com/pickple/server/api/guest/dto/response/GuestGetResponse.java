package com.pickple.server.api.guest.dto.response;

import lombok.Builder;

@Builder
public record GuestGetResponse(

        String guestImageUrl,

        String guestNickname
) {
}
