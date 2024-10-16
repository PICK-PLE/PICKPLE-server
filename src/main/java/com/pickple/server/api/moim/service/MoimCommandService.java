package com.pickple.server.api.moim.service;

import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.domain.enums.MoimState;
import com.pickple.server.api.moim.dto.request.MoimCreateRequest;
import com.pickple.server.api.moim.dto.response.MoimCreateResponse;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import com.pickple.server.api.moimsubmission.repository.MoimSubmissionRepository;
import com.pickple.server.global.util.DateUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MoimCommandService {

    private final HostRepository hostRepository;
    private final MoimRepository moimRepository;
    private final MoimSubmissionRepository moimSubmissionRepository;

    public MoimCreateResponse createMoim(Long hostId, MoimCreateRequest request) {
        Host host = hostRepository.findHostByIdOrThrow(hostId);
        Moim moim = Moim.builder()
                .host(host)
                .categoryList(request.categoryList())
                .isOffline(request.isOffline())
                .spot(request.spot())
                .dateList(request.dateList())
                .maxGuest(request.maxGuest())
                .fee(request.fee())
                .accountList(request.accountList())
                .questionList(request.questionList())
                .title(request.title())
                .description(request.description())
                .imageList(request.imageList())
                .moimState(MoimState.ONGOING.getMoimState())
                .build();
        moimRepository.save(moim);
        return MoimCreateResponse.builder()
                .moimId(moim.getId())
                .build();
    }

    //정각마다 실행
    @Scheduled(cron = "0 0 0/1 * * *", zone = "Asia/Seoul")
    public void changeMoimStateOfDday() {
        LocalDate date = LocalDate.now();
        String formattedDate = DateUtil.refineDate(date);
        List<Moim> moimList = moimRepository.findMoimsByDate(formattedDate);

        for (Moim moim : moimList) {
            if (moim.getDateList().getEndTime().isBefore(LocalTime.now())) {
                moim.updateMoimState("completed");
                List<MoimSubmission> moimSubmissionList = moimSubmissionRepository.findApprovedMoimSubmissionsByMoimId(
                        moim.getId());
                for (MoimSubmission moimSubmission : moimSubmissionList) {
                    moimSubmission.updateMoimSubmissionState("completed");
                }
            }
        }
    }
}
