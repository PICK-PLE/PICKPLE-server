package com.pickple.server.api.moimsubmission.dto.response;

import com.pickple.server.api.moimsubmission.domain.SubmitterInfo;
import java.util.List;
import lombok.Builder;

@Builder
public record MoimSubmissionByMoimResponse(
        int maxGuest,    //신청자 최대 인원
        Boolean isApprovable,    //승인가능여부
        List<SubmitterInfo> submitterList    //신청자 리스트
) {
}
