package com.pickple.server.api.guest.service;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.dto.request.GuestUpdateRequest;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.api.submitter.repository.SubmitterRepository;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GuestCommandService {

    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;
    private final SubmitterRepository submitterRepository;

    public void updateGuestNickname(Long guestId, GuestUpdateRequest guestUpdateRequest) {
        Guest guest = guestRepository.findGuestByIdOrThrow(guestId);
        isDuplicatedNickname(guestUpdateRequest.guestNickname());
        guest.updateNickname(guestUpdateRequest.guestNickname());
        guestRepository.save(guest);
    }

    public void isDuplicatedNickname(String nickname) {
        if (hostRepository.existsByNickname(nickname) || guestRepository.existsByNickname(nickname) ||
                submitterRepository.existsByNicknameAndSubmitterState(nickname, "pending")) {
            throw new CustomException(ErrorCode.DUPLICATION_NICKNAME);
        }
    }
}
