package com.pickple.server.api.user.dto;

public record LoginSuccessResponse(
        String guestNickname,
        Long guestId,
        String hostNickname,
        Long hostId,
        TokenDto token
) {
    public static LoginSuccessResponse of(
            final String guestNickname,
            final Long guestId,
            final String hostNickname,
            final Long hostId,
            final TokenDto token
    ) {
        return new LoginSuccessResponse(guestNickname, guestId, hostNickname, hostId, token);
    }
}