package com.pickple.server.api.moimsubmission.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record MoimSubmitterUpdateRequest(

        @NotNull
        List<Long> submitterIdList
) {
}
