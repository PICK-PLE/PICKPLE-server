package com.pickple.server.api.moimsubmission.dto.request;

import java.util.List;

public record MoimSubmitterUpdateRequest(
        List<Long> submitterIdList
) {
}
