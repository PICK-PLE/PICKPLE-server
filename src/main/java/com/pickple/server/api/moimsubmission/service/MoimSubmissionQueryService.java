package com.pickple.server.api.moimsubmission.service;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.host.dto.response.SubmittionDetailResponse;
import com.pickple.server.api.moim.dto.response.SubmittedMoimByGuestResponse;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import com.pickple.server.api.moimsubmission.domain.MoimSubmissionState;
import com.pickple.server.api.moimsubmission.domain.SubmitterInfo;
import com.pickple.server.api.moimsubmission.dto.response.MoimSubmissionByMoimResponse;
import com.pickple.server.api.moimsubmission.repository.MoimSubmissionRepository;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import com.pickple.server.global.util.DateTimeUtil;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                                                                          final String moimSubmissionState) {
        Guest guest = guestRepository.findGuestByIdOrThrow(guestId);
        List<MoimSubmission> moimSubmissionList;

        if (moimSubmissionState.equals(MoimSubmissionState.ALL.getMoimSubmissionState())) {
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

        if (submission == null) {
            throw new CustomException(ErrorCode.MOIM_SUBMISSION_NOT_FOUND);
        }

        return SubmittionDetailResponse.builder()
                .answerList(submission.getAnswerList())
                .questionList(submission.getMoim().getQuestionList())
                .build();
    }

    public List<SubmittedMoimByGuestResponse> getCompletedMoimListByGuest(final Long guestId) {
        Guest guest = guestRepository.findGuestByIdOrThrow(guestId);
        List<MoimSubmission> moimSubmissionList = moimSubmissionRepository.findCompletedMoimSubmissionsByGuest(
                guest.getId());

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

    public List<MoimSubmissionByMoimResponse> getSubmitterListByMoim(Long moimId) {
        List<MoimSubmission> moimSubmissionList = moimSubmissionRepository.findMoimListByMoimId(moimId);

        if (moimSubmissionList.isEmpty()) {
            throw new CustomException(ErrorCode.SUBMITTE_BY_MOIM_NOT_FOUND);
        }

        return moimSubmissionList.stream()
                .map(oneMoimSubmission -> MoimSubmissionByMoimResponse.builder()
                        .maxGuest(oneMoimSubmission.getMoim().getMaxGuest())
                        .isApprovable(isApprovable(oneMoimSubmission))
                        .submitterList(getSubmitterInfo(oneMoimSubmission.getGuestId()))
                        .build())
                .collect(Collectors.toList());
    }

    private boolean isApprovable(MoimSubmission oneMoimSubmission) {
        // 신청일
        LocalDateTime createdAt = oneMoimSubmission.getCreatedAt();

        // 마감일: 신청일 + 3일의 자정
        LocalDateTime deadline = createdAt.plusDays(3).with(LocalTime.MIDNIGHT);

        // 현재 시간
        LocalDateTime now = LocalDateTime.now();

        // 승인 가능 여부: 현재 시간이 마감일 이후인지 확인
        return now.isAfter(deadline);
    }

    public SubmitterInfo getSubmitterInfo(Long guestId) {
        // Guest 객체를 가져옴
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new CustomException(ErrorCode.GUEST_NOT_FOUND));

        // SubmitterInfo 객체 생성
        return new SubmitterInfo(
                guest.getId(),
                guest.getNickname(),
                guest.getImageUrl(),
                DateTimeUtil.refineDateAndTime(guest.getCreatedAt())
        );
    }
}
