package com.pickple.server.api.host.service;

import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.host.dto.response.HostGetResponse;
import com.pickple.server.api.host.repository.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HostQueryService {

    private final HostRepository hostRepository;

    public HostGetResponse getHost(Long hostId) {
        Host host = hostRepository.findHostByIdOrThrow(hostId);
        return HostGetResponse.builder()
                .hostId(host.getId())
                .hostCategoryList(host.getCategoryList())
                .hostImageUrl(host.getImageUrl())
                .hostNickName(host.getNickname())
                .hostLink(host.getLink())
                .build();
    }
}
