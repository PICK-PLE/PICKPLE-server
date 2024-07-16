package com.pickple.server.api.moimsubmission.service;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import com.pickple.server.api.moimsubmission.domain.MoimSubmissionState;
import com.pickple.server.api.moimsubmission.dto.request.MoimSubmitRequest;
import com.pickple.server.api.moimsubmission.repository.MoimSubmissionRepository;
import com.pickple.server.global.exception.BadRequestException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MoimSubmissionCommandService {

    private final MoimRepository moimRepository;
    private final MoimSubmissionRepository moimSubmissionRepository;

    public void createMoimSubmission(Long moimId, Long guestId, MoimSubmitRequest request) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);
        MoimSubmission moimSubmission = MoimSubmission.builder()
                .guestId(guestId)
                .moim(moim)
                .answerList(request.answerList())
                .accountList(request.accountList())
                .moimSubmissionState(MoimSubmissionState.PENDING_PAYMENT.getMoimSubmissionState())
                .build();
        isDuplicatedMoimSubmission(moimSubmission);
        moimSubmissionRepository.save(moimSubmission);
    }

    private void isDuplicatedMoimSubmission(MoimSubmission moimSubmission) {
        if (moimSubmissionRepository.existsByMoimAndMoimSubmissionStateAndGuestId(moimSubmission.getMoim(),
                moimSubmission.getMoimSubmissionState(), moimSubmission.getGuestId())) {
            throw new BadRequestException(ErrorCode.DUPLICATION_MOIM_SUBMISSION);
        }
    }

    public void updateSubmissionState(Long moimId, List<Long> submitterIdList) {
        List<MoimSubmission> moimSubmissionList = moimSubmissionRepository.findByMoimIdAndGuestIdIn(moimId,
                submitterIdList);
        for (MoimSubmission moimSubmission : moimSubmissionList) {
            moimSubmission.updateMoimSubmissionState(MoimSubmissionState.APPROVED.getMoimSubmissionState());
            moimSubmissionRepository.save(moimSubmission);
        }
    }
}
