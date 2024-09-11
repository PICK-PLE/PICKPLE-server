package com.pickple.server.api.guest.service;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.dto.response.GuestGetResponse;
import com.pickple.server.api.guest.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuestQueryService {

    private final GuestRepository guestRepository;

    public GuestGetResponse getGuest(final Long guestId) {
        Guest guest = guestRepository.findGuestByUserId(guestId);
        return GuestGetResponse.builder()
                .guestImageUrl(guest.getImageUrl())
                .guestNickname(guest.getNickname())
                .build();
    }

}
