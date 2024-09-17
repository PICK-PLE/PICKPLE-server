package com.pickple.server.api.submitter.service;


import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.api.submitter.domain.Submitter;
import com.pickple.server.api.submitter.domain.SubmitterState;
import com.pickple.server.api.submitter.dto.request.SubmitterCreateRequest;
import com.pickple.server.api.submitter.repository.SubmitterRepository;
import com.pickple.server.api.user.domain.Role;
import com.pickple.server.global.exception.BadRequestException;
import com.pickple.server.global.exception.CustomException;
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
    private final HostRepository hostRepository;

    public void createSubmitter(Long guestId, SubmitterCreateRequest request) {
        Guest guest = guestRepository.findGuestByIdOrThrow(guestId);
        isDuplicatedNickname(request.nickname());
        Submitter submitter = Submitter.builder()
                .guest(guest)
                .intro(request.intro())
                .goal(request.goal())
                .link(request.link())
                .nickname(request.nickname())
                .email(request.email())
                .plan(request.plan())
                .userKeyword(request.userKeyword())
                .submitterState(SubmitterState.PENDING.getSubmitterState())
                .build();
        isDuplicatedSubmission(submitter);
        submitterRepository.save(submitter);
    }

    public void approveSubmitter(Long submitterId) {
        Submitter submitter = submitterRepository.findSubmitterByIdOrThrow(submitterId);

        submitter.updateSubmitterState(SubmitterState.APPROVE.getSubmitterState());

        //호스트 승인 시 role host로 변경
        submitter.getGuest().getUser().updateRole(Role.HOST.getRole());

        Host host = Host.builder()
                .user(submitter.getGuest().getUser())
                .link(submitter.getLink())
                .imageUrl("https://pickple-bucket.s3.ap-northeast-2.amazonaws.com/profile/hostProfileImage.png")
                .nickname(submitter.getNickname())
                .userKeyword(submitter.getUserKeyword())
                .description("안녕하세요, 스픽커 " + submitter.getNickname() + "입니다.")
                .build();

        hostRepository.save(host);
    }

    private void isDuplicatedSubmission(Submitter submitter) {
        if (submitterRepository.existsByGuestAndSubmitterState(submitter.getGuest(), submitter.getSubmitterState())) {
            throw new BadRequestException(ErrorCode.DUPLICATION_SUBMITTER);
        }
    }

    private void isDuplicatedNickname(String nickname) {
        if (hostRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.DUPLICATION_NICKNAME);
        } else if (submitterRepository.existsByNicknameAndSubmitterState(nickname, "pending")) {
            throw new CustomException(ErrorCode.DUPLICATION_NICKNAME);
        }
    }
}