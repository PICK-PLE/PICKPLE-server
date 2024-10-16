package com.pickple.server.api.moimsubmission.service;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.host.dto.response.SubmittionDetailResponse;
import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.dto.response.SubmittedMoimByGuestResponse;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import com.pickple.server.api.moimsubmission.domain.MoimSubmissionState;
import com.pickple.server.api.moimsubmission.domain.SubmitterInfo;
import com.pickple.server.api.moimsubmission.dto.response.MoimSubmissionAllResponse;
import com.pickple.server.api.moimsubmission.dto.response.MoimSubmissionByMoimResponse;
import com.pickple.server.api.moimsubmission.repository.MoimSubmissionRepository;
import com.pickple.server.api.review.repository.ReviewRepository;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import com.pickple.server.global.util.DateTimeUtil;
import java.time.LocalDate;
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
    private final MoimRepository moimRepository;
    private final ReviewRepository reviewRepository;

    public List<SubmittedMoimByGuestResponse> getSubmittedMoimListByGuest(final Long guestId,
                                                                          final String moimSubmissionState) {
        Guest guest = guestRepository.findGuestByIdOrThrow(guestId);
        List<MoimSubmission> moimSubmissionList;

        if (moimSubmissionState.equals(MoimSubmissionState.ALL.getMoimSubmissionState())) {
            moimSubmissionList = moimSubmissionRepository.findSubmittedMoimSubmissions(guest.getId());
        } else {
            moimSubmissionList = moimSubmissionRepository.findMoimSubmissionsByGuestIdAndMoimSubmissionState
                    (guestId, moimSubmissionState);
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

    public SubmittionDetailResponse getSubmissionDetail(Long moimId, Long guestId) {
        MoimSubmission submission = moimSubmissionRepository.findMoimSubmissionByMoimIdAndGuestId(moimId, guestId);

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

        return moimSubmissionList.stream()
                .map(oneMoimSubmission -> SubmittedMoimByGuestResponse.builder()
                        .moimId(oneMoimSubmission.getMoim().getId())
                        .moimSubmissionState(oneMoimSubmission.getMoimSubmissionState())
                        .title(oneMoimSubmission.getMoim().getTitle())
                        .hostNickname(oneMoimSubmission.getMoim().getHost().getNickname())
                        .dateList(oneMoimSubmission.getMoim().getDateList())
                        .fee(oneMoimSubmission.getMoim().getFee())
                        .imageUrl(oneMoimSubmission.getMoim().getImageList().getImageUrl1())
                        .isReviewed(reviewRepository.existsByMoimIdAndGuestId(oneMoimSubmission.getMoim().getId(),
                                guest.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    public MoimSubmissionByMoimResponse getSubmitterListByMoim(Long moimId) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);

        // moimSubmissionList 가져오기
        List<MoimSubmission> moimSubmissionList = moimSubmissionRepository
                .findMoimSubmissionsByMoimIdAndMoimSubmissionState(moimId);

        // guestId를 이용하여 SubmitterInfo 객체 생성 후 리스트에 저장
        List<SubmitterInfo> submitterInfoList = moimSubmissionList.stream()
                .map(moimSubmission -> getSubmitterInfo(moimSubmission.getGuestId(), moimId))
                .collect(Collectors.toList());

        return MoimSubmissionByMoimResponse
                .builder()
                .moimTitle(moim.getTitle())
                .moimDate(moim.getDateList().getDate())
                .maxGuest(moim.getMaxGuest())
                .isApprovable(isApprovable(moim))
                .isMoimSubmissionApproved(isMoimSubmissonApproved(moimId))
                .isOngoing(isOngoing(moimId))
                .submitterList(submitterInfoList)
                .build();
    }

    public List<MoimSubmissionAllResponse> getAllMoimSubmissionList() {
        List<MoimSubmission> moimSubmissionList = moimSubmissionRepository.findAll();

        return moimSubmissionList.stream()
                .map(this::mapToMoimSubmissionAllResponse)
                .collect(Collectors.toList());
    }

    private MoimSubmissionAllResponse mapToMoimSubmissionAllResponse(MoimSubmission moimSubmission) {
        Guest guest = guestRepository.findGuestByIdOrThrow(moimSubmission.getGuestId());

        return MoimSubmissionAllResponse.builder()
                .moimSubmissionId(moimSubmission.getId())
                .date(DateTimeUtil.refineDateAndTime(moimSubmission.getCreatedAt()))
                .moimSubmissionState(moimSubmission.getMoimSubmissionState())
                .moimId(moimSubmission.getMoim().getId())
                .moimTitle(moimSubmission.getMoim().getTitle())
                .hostNickname(moimSubmission.getMoim().getHost().getNickname())
                .guestId(moimSubmission.getGuestId())
                .kakaoNickname(guest.getUser().getSocialNickname())
                .guestNickname(guest.getNickname())
                .questionList(moimSubmission.getMoim().getQuestionList())
                .answerList(moimSubmission.getAnswerList())
                .accountList(moimSubmission.getAccountList())
                .build();
    }

    private boolean isApprovable(Moim moim) {
        // 모임일
        LocalDate date = moim.getDateList().getDate();

        // 모임 시작 시간
        LocalTime startTime = moim.getDateList().getStartTime();

        // 승인 가능 시작 시간: 모임일 전날 자정 (모임일 하루 전 자정)
        LocalDateTime approvalStartTime = date.minusDays(1).atStartOfDay();

        // 승인 가능 종료 시간: 모임일의 시작 시간
        LocalDateTime approvalEndTime = date.atTime(startTime);

        // 현재 시간
        LocalDateTime now = LocalDateTime.now();

        // 승인 가능 여부: 현재 시간이 승인 시작 시간 이후 또는 같은 시간이고, 승인 종료 시간 이전인지 확인
        return (now.isEqual(approvalStartTime) || now.isAfter(approvalStartTime)) && now.isBefore(approvalEndTime);
    }

    public SubmitterInfo getSubmitterInfo(Long guestId, Long moimId) {
        // Guest 객체를 가져옴
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new CustomException(ErrorCode.GUEST_NOT_FOUND));

        //MoimSubmission 객체를 가져옴
        MoimSubmission moimSubmission = moimSubmissionRepository.findMoimSubmissionByMoimIdAndGuestId(moimId, guestId);

        // SubmitterInfo 객체 생성
        return new SubmitterInfo(
                guest.getId(),
                guest.getNickname(),
                guest.getImageUrl(),
                DateTimeUtil.refineDateAndTime(moimSubmission.getCreatedAt()),
                moimSubmission.getMoimSubmissionState()
        );
    }

    public boolean isMoimSubmissonApproved(Long moimId) {
        List<MoimSubmission> moimSubmissionList = moimSubmissionRepository.findMoimSubmissionsByMoimId(moimId);

        //모임에 해당하는 신청 내역 중 approved or rejected가 있는 경우 true 리턴
        for (MoimSubmission moimSubmission : moimSubmissionList) {
            if (moimSubmission.getMoimSubmissionState().equals(MoimSubmissionState.APPROVED.getMoimSubmissionState()) ||
                    moimSubmission.getMoimSubmissionState()
                            .equals(MoimSubmissionState.REJECTED.getMoimSubmissionState())) {
                return true;
            }
        }
        return false;
    }

    public boolean isOngoing(Long moimId) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);
        return moim.getMoimState().equals("ongoing");
    }
}
