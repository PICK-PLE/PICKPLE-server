package com.pickple.server.api.host.service;

import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.host.dto.response.HostByMoimResponse;
import com.pickple.server.api.host.dto.response.HostGetResponse;
import com.pickple.server.api.host.dto.response.HostIntroGetResponse;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.moimsubmission.repository.MoimSubmissionRepository;
import com.pickple.server.api.submitter.domain.Submitter;
import com.pickple.server.api.submitter.repository.SubmitterRepository;
import com.pickple.server.global.exception.BadRequestException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HostQueryService {

    private final HostRepository hostRepository;
    private final MoimRepository moimRepository;
    private final MoimSubmissionRepository moimSubmissionRepository;
    private final SubmitterRepository submitterRepository;

    public HostGetResponse getHost(Long hostId, Long guestId) {
        Submitter submitter = submitterRepository.findSubmitterByGuestIdOrThrow(guestId);
        isDuplicatedSubmission(submitter);

        Host host = hostRepository.findHostByIdOrThrow(hostId);

        return HostGetResponse.builder()
                .hostId(host.getId())
                .hostImageUrl(host.getImageUrl())
                .hostNickName(host.getNickname())
                .hostLink(host.getLink())
                .keyword(host.getUserKeyword())
                .attendeeCount(attendeeCounter(hostId))
                .moimCount(moimCounter(hostId))
                .build();
    }

    public HostByMoimResponse getHostByMoim(Long hostId) {
        Host host = hostRepository.findHostByIdOrThrow(hostId);

        return HostByMoimResponse.builder()
                .hostNickName(host.getNickname())
                .hostImageUrl(host.getImageUrl())
                .count(moimRepository.countByHostId(hostId))
                .keyword(host.getUserKeyword())
                .description(host.getDescription())
                .build();
    }

    public HostIntroGetResponse getHostIntro(Long hostId) {
        Host host = hostRepository.findHostByIdOrThrow(hostId);

        return HostIntroGetResponse.builder()
                .nickName(host.getNickname())
                .profileUrl(host.getImageUrl())
                .count(moimRepository.countByHostId(hostId))
                .keyword(host.getUserKeyword())
                .description(host.getDescription())
                .socialLink(host.getLink())
                .build();
    }

    private int attendeeCounter(Long hostId) {
        List<Moim> moimList = moimRepository.findCompletedMoimsByHostId(hostId);

        List<Long> moimIds = moimList.stream()
                .map(Moim::getId) // Moim 엔티티에서 id를 추출
                .collect(Collectors.toList());

        return moimSubmissionRepository.countApprovedSubmissionsByMoimIds(moimIds);
    }

    private int moimCounter(Long hostId) {
        return moimRepository.CompletedMoimNumber(hostId);
    }

    private void isDuplicatedSubmission(Submitter submitter) {
        if (submitterRepository.existsByGuestAndSubmitterState(submitter.getGuest(), submitter.getSubmitterState())) {
            throw new BadRequestException(ErrorCode.DUPLICATION_SUBMITTER);
        }
    }

}
