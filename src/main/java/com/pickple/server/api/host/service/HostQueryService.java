package com.pickple.server.api.host.service;

import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.host.dto.response.HostByMoimResponse;
import com.pickple.server.api.host.dto.response.HostGetResponse;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.moimsubmission.repository.MoimSubmissionRepository;
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

    public HostGetResponse getHost(Long hostId) {
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
                .userKeyword(host.getUserKeyword())
                .description(host.getDescription())
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
}
