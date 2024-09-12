package com.pickple.server.api.submitter.service;


import com.pickple.server.api.submitter.domain.Submitter;
import com.pickple.server.api.submitter.dto.response.SubmitterListGetResponse;
import com.pickple.server.api.submitter.repository.SubmitterRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubmitterQueryService {

    private final SubmitterRepository submitterRepository;

    public List<SubmitterListGetResponse> getSubmitterList() {
        List<Submitter> submitterList = submitterRepository.findAll();

        return submitterList.stream()
                .map(submitter -> SubmitterListGetResponse.builder()
                        .guestNickname(submitter.getGuest().getNickname())
                        .guestId(submitter.getGuest().getId())
                        .submitterId(submitter.getId())
                        .intro(submitter.getIntro())
                        .goal(submitter.getGoal())
                        .link(submitter.getLink())
                        .nickname(submitter.getNickname())
                        .userKeyword(submitter.getUserKeyword())
                        .plan(submitter.getPlan())
                        .email(submitter.getEmail())
                        .submitterState(submitter.getSubmitterState())
                        .build())
                .collect(Collectors.toList());
    }
}
