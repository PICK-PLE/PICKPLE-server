package com.pickple.server.api.host.service;


import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.host.dto.request.HostUpdateRequest;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.api.submitter.domain.SubmitterState;
import com.pickple.server.api.submitter.repository.SubmitterRepository;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HostCommandService {

    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;
    private final SubmitterRepository submitterRepository;

    public void updateHostProfile(Long hostId, HostUpdateRequest hostUpdateRequest) {
        Host host = hostRepository.findHostByIdOrThrow(hostId);
        if (!host.getNickname().equals(hostUpdateRequest.nickname())
                && (hostRepository.existsByNickname(hostUpdateRequest.nickname())
                || guestRepository.existsByNickname(hostUpdateRequest.nickname())
                || submitterRepository.existsByNicknameAndSubmitterState(hostUpdateRequest.nickname(),
                SubmitterState.PENDING.getSubmitterState()))) {
            throw new CustomException(ErrorCode.DUPLICATION_NICKNAME);
        }

        host.updateHostProfile(hostUpdateRequest.profileUrl(), hostUpdateRequest.nickname(),
                hostUpdateRequest.keyword(),
                hostUpdateRequest.description(), hostUpdateRequest.socialLink());

        submitterRepository.findSubmitterByGuestId(guestRepository.findGuestByUserId(host.getUser().getId()).getId())
                .updateSubmitterNickname(hostUpdateRequest.nickname());

    }
}
