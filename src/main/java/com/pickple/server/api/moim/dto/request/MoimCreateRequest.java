package com.pickple.server.api.moim.dto.request;

import com.pickple.server.api.moim.domain.CategoryInfo;
import com.pickple.server.api.moim.domain.DateInfo;
import com.pickple.server.api.moim.domain.ImageInfo;
import com.pickple.server.api.moim.domain.QuestionInfo;
import com.pickple.server.api.moimsubmission.domain.AccountInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public record MoimCreateRequest(
        @Valid
        CategoryInfo categoryList,

        boolean isOffline,

        @NotBlank(message = "모임 장소가 비어있습니다.")
        String spot,

        @Valid
        DateInfo dateList,

        @NotNull(message = "모임 정원이 비어있습니다.")
        int maxGuest,

        @NotNull(message = "참가비가 비어있습니다.")
        int fee,

        @Valid
        AccountInfo accountList,

        @Valid
        QuestionInfo questionList,

        @Size(max = 28, message = "28자 이내로 작성해 주세요.")
        @NotBlank(message = "제목이 비어있습니다.")
        String title,

        @Size(max = 2000, message = "2000자 이내로 작성해 주세요.")
        @NotBlank(message = "소개글이 비어있습니다.")
        String description,

        @Valid
        ImageInfo imageList
) {
}
