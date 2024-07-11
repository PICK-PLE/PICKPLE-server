package com.pickple.server.api.moim.dto.request;

import com.pickple.server.api.moim.domain.CategoryInfo;
import com.pickple.server.api.moim.domain.DateInfo;
import com.pickple.server.api.moim.domain.ImageInfo;
import com.pickple.server.api.moim.domain.QuestionInfo;
import com.pickple.server.api.moimsubmission.domain.AccountInfo;

public record MoimCreateRequest(
        CategoryInfo categoryList,
        boolean isOffline,
        String spot,
        DateInfo dateList,
        int maxGuest,
        int fee,
        AccountInfo accountList,
        QuestionInfo questionList,
        String title,
        String description,
        ImageInfo imageList
) {
}
