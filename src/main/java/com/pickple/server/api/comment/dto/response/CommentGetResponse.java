package com.pickple.server.api.comment.dto.response;

import lombok.Builder;

@Builder
public record CommentGetResponse(

        Long commentId,

        boolean isOwner,

        String commenterImageUrl,

        String commenterNickname,

        String commentContent,

        String commentDate,

        boolean isVeteran

) {
}
