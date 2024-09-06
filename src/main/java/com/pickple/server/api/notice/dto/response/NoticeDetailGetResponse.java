package com.pickple.server.api.notice.dto.response;

import lombok.Builder;

@Builder
public record NoticeDetailGetResponse(

        String hostImageUrl,

        String hostNickname,

        String title,

        String content,

        String noticeImageUrl,

        String dateTime,

        int commentNumber,

        boolean isPrivate,

        boolean isOwner
) {
}
