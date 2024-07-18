package com.pickple.server.api.moim.dto.request;

import com.pickple.server.api.moim.domain.CategoryInfo;
import com.pickple.server.api.moim.domain.DateInfo;
import com.pickple.server.api.moim.domain.ImageInfo;
import com.pickple.server.api.moim.domain.QuestionInfo;
import com.pickple.server.api.moimsubmission.domain.AccountInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MoimCreateRequest(
        CategoryInfo categoryList,

        @NotNull(message = "오프라인 여부가 비어있습니다")
        boolean isOffline,

        @NotBlank(message = "모임 장소가 비어있습니다.")
        String spot,

        DateInfo dateList,

        @NotNull(message = "모임 정원이 비어있습니다.")
        int maxGuest,

        @NotNull(message = "참가비가 비어있습니다.")
        int fee,

        AccountInfo accountList,
        QuestionInfo questionList,

        @Size(max = 28, message = "28자 이내로 작성해 주세요.")
        @NotBlank(message = "제목이 비어있습니다.")
        String title,

        @Size(max = 2000, message = "2000자 이내로 작성해 주세요.")
        @NotBlank(message = "소개글이 비어있습니다.")
        String description,

        ImageInfo imageList
) {
}
