package com.pickple.server.api.notice.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record NoticeDetailGetResponse(
        String hostImageUrl,
        String hostNickname,
        String title,
        String content,
        LocalDateTime dateTime,
        int commentNumber,
        boolean isPrivate,
        boolean isOwner
) {
}
