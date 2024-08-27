package com.pickple.server.api.host.service;

import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.host.dto.response.HostByMoimResponse;
import com.pickple.server.api.host.dto.response.HostGetResponse;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.repository.MoimRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HostQueryService {

    private final HostRepository hostRepository;
    private final MoimRepository moimRepository;

    public HostGetResponse getHost(Long hostId) {
        Host host = hostRepository.findHostByIdOrThrow(hostId);
        return HostGetResponse.builder()
                .hostId(host.getId())
                .hostImageUrl(host.getImageUrl())
                .hostNickName(host.getNickname())
                .hostLink(host.getLink())
                .build();
    }

    public HostByMoimResponse getHostByMoim(Long hostId) {
        Host host = hostRepository.findHostByIdOrThrow(hostId);
        List<Moim> moimList = moimRepository.findMoimByHostId(hostId);
        String count = String.format("%02d", moimList.size());

        return HostByMoimResponse.builder()
                .hostNickName(host.getNickname())
                .hostImageUrl(host.getImageUrl())
                .count(count)
                .build();
    }
}
