package com.pickple.server.api.host.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record HostUpdateRequest(
        String profileUrl,

        @Size(max = 50)
        @NotBlank(message = "닉네임이 비어있습니다.")
        String nickname,

        @Size(max = 50)
        @NotBlank(message = "호칭이 비어있습니다.")
        String keyword,

        @Size(max = 70)
        @NotBlank(message = "소개글이 비어있습니다.")
        String description,

        @Size(max = 50)
        @NotBlank(message = "소셜 링크가 비어있습니다.")
        String socialLink
) {
}
