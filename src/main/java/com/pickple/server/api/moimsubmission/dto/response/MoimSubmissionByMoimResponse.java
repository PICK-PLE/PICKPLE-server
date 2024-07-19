package com.pickple.server.api.moimsubmission.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pickple.server.api.moimsubmission.domain.SubmitterInfo;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record MoimSubmissionByMoimResponse(
        String moimTitle,

        @JsonFormat(pattern = "yyyy.MM.dd")
        LocalDate moimDate,
        int maxGuest,    //신청자 최대 인원
        Boolean isApprovable,    //승인가능여부

        boolean isMoimSubmissionApproved,
        boolean isOngoing,
        List<SubmitterInfo> submitterList    //신청자 리스트
) {
}
