package com.pickple.server.api.submitter.service;


import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.submitter.domain.Submitter;
import com.pickple.server.api.submitter.domain.SubmitterState;
import com.pickple.server.api.submitter.dto.request.SubmitterCreateRequest;
import com.pickple.server.api.submitter.repository.SubmitterRepository;
import com.pickple.server.global.exception.BadRequestException;
import com.pickple.server.global.response.enums.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmitterCommandService {

    private final GuestRepository guestRepository;
    private final SubmitterRepository submitterRepository;

    public void createSubmitter(Long guestId, SubmitterCreateRequest request) {
        Guest guest = guestRepository.findGuestByIdOrThrow(guestId);
        Submitter submitter = Submitter.builder()
                .guest(guest)
                .intro(request.intro())
                .goal(request.goal())
                .link(request.link())
                .nickname(request.nickname())
                .category(request.categoryList())
                .email(request.email())
                .plan(request.plan())
                .submitterState(SubmitterState.PENDING)
                .build();
        isDulicatedSubmition(submitter);
        submitterRepository.save(submitter);
    }

    private void isDulicatedSubmition(Submitter submitter) {
        if (submitterRepository.existsByGuestAndSubmitterState(submitter.getGuest(), submitter.getSubmitterState())) {
            throw new BadRequestException(ErrorCode.DUPLICATION_SUBMITTER);
        }
    }
}