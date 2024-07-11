package com.pickple.server.api.moim.service;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.dto.response.MoimDetailResponse;
import com.pickple.server.api.moim.dto.response.SubmittedMoimResponse;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.global.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MoimQueryService {

    private final MoimRepository moimRepository;

    public MoimDetailResponse getMoimDetail(final Long moimId) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);
        return MoimDetailResponse.of(
                DateUtil.calculateDayOfDay(moim.getDateList().getDate()),
                moim.getTitle(),
                moim.getDateList(),
                moim.isOffline(),
                moim.getSpot(),
                moim.getMaxGuest(),
                moim.getFee(),
                moim.getImageList(),
                moim.getHost().getId());
    }

    public SubmittedMoimResponse getSubmittedMoimDetail(final Long moimId) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);
        return SubmittedMoimResponse.of(
                moim.getTitle(),
                moim.getHost().getNickname(),
                moim.isOffline(),
                moim.getSpot(),
                moim.getDateList(),
                moim.getFee(),
                moim.getHost().getImageUrl(),
                moim.getImageList().getImageUrl1()
        );
    }
}
