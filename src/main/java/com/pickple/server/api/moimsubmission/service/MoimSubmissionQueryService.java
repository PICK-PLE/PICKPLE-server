package com.pickple.server.api.moimsubmission.service;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.host.dto.response.SubmittionDetailResponse;
import com.pickple.server.api.moim.dto.response.SubmittedMoimByGuestResponse;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import com.pickple.server.api.moimsubmission.domain.MoimSubmissionState;
import com.pickple.server.api.moimsubmission.repository.MoimSubmissionRepository;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MoimSubmissionQueryService {

    private final GuestRepository guestRepository;
    private final MoimSubmissionRepository moimSubmissionRepository;

    public List<SubmittedMoimByGuestResponse> getSubmittedMoimListByGuest(final Long guestId,
                                                                          final MoimSubmissionState moimSubmissionState) {
        Guest guest = guestRepository.findGuestByIdOrThrow(guestId);
        List<MoimSubmission> moimSubmissionList;

        if (moimSubmissionState.equals(MoimSubmissionState.ALL)) {
            moimSubmissionList = moimSubmissionRepository.findAllByGuestId(guest.getId());
        } else {
            moimSubmissionList = moimSubmissionRepository.findAllByMoimSubmissionState(moimSubmissionState);
        }

        if (moimSubmissionList.isEmpty()) {
            throw new CustomException(ErrorCode.MOIM_BY_STATE_NOT_FOUND);
        }

        return moimSubmissionList.stream()
                .map(oneMoimSubmission -> SubmittedMoimByGuestResponse.builder()
                        .moimId(oneMoimSubmission.getMoim().getId())
                        .moimSubmissionState(oneMoimSubmission.getMoimSubmissionState())
                        .title(oneMoimSubmission.getMoim().getTitle())
                        .hostNickname(oneMoimSubmission.getMoim().getHost().getNickname())
                        .dateList(oneMoimSubmission.getMoim().getDateList())
                        .fee(oneMoimSubmission.getMoim().getFee())
                        .imageUrl(oneMoimSubmission.getMoim().getImageList().getImageUrl1())
                        .build())
                .collect(Collectors.toList());
    }

    public SubmittionDetailResponse getSubmittionDetail(Long moimId, Long guestId) {
        MoimSubmission submission = moimSubmissionRepository.findBymoimIdAndGuestId(moimId, guestId);

        return SubmittionDetailResponse.builder()
                .answerList(submission.getAnswerList())
                .questionList(submission.getMoim().getQuestionList())
                .build();
    }
}
