package com.pickple.server.api.moim.service;

import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.domain.enums.MoimState;
import com.pickple.server.api.moim.dto.request.MoimCreateRequest;
import com.pickple.server.api.moim.repository.MoimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MoimCommandService {

    private final HostRepository hostRepository;
    private final MoimRepository moimRepository;

    public void createMoim(Long hostId, MoimCreateRequest request) {
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
    }
}
