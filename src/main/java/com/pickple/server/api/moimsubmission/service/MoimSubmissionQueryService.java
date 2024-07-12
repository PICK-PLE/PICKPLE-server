package com.pickple.server.api.moimsubmission.service;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.moim.dto.response.SubmittedMoimByGuestResponse;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import com.pickple.server.api.moimsubmission.domain.MoimSubmissionState;
import com.pickple.server.api.moimsubmission.repository.MoimSubmissionRepository;
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
        if (moimSubmissionState.equals(MoimSubmissionState.ALL)) {
            List<MoimSubmission> moimSubmissionList = moimSubmissionRepository.findAllByGuestId(guest.getId());
            return moimSubmissionList.stream()
                    .map(oneMoimSubmission -> SubmittedMoimByGuestResponse.builder()
                            .moimId(oneMoimSubmission.getMoim().getId())
                            .moimSubmissionState(oneMoimSubmission.getMoimSubmissionState())
                            .title(oneMoimSubmission.getMoim().getTitle())
                            .hostNickname(oneMoimSubmission.getMoim().getHost().getNickname())
                            .dateList(oneMoimSubmission.getMoim().getDateList())
                            .fee(oneMoimSubmission.getMoim().getFee())
                            .imageUrl(oneMoimSubmission.getMoim().getImageList().getImageUrl1())
                            .build()
                    ).collect(Collectors.toList());
        } else {
            List<MoimSubmission> moimSubmissionList = moimSubmissionRepository.findAllByMoimSubmissionState(
                    moimSubmissionState);
            return moimSubmissionList.stream()
                    .map(oneMoimSubmission -> SubmittedMoimByGuestResponse.builder()
                            .moimId(oneMoimSubmission.getMoim().getId())
                            .moimSubmissionState(oneMoimSubmission.getMoimSubmissionState())
                            .title(oneMoimSubmission.getMoim().getTitle())
                            .hostNickname(oneMoimSubmission.getMoim().getHost().getNickname())
                            .dateList(oneMoimSubmission.getMoim().getDateList())
                            .fee(oneMoimSubmission.getMoim().getFee())
                            .imageUrl(oneMoimSubmission.getMoim().getImageList().getImageUrl1())
                            .build()
                    ).collect(Collectors.toList());
        }
    }
}
