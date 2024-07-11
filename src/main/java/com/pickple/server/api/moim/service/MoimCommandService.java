package com.pickple.server.api.moim.service;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.dto.response.MoimDetailResponse;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import com.pickple.server.global.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MoimCommandService {

    private final MoimRepository moimRepository;

    public MoimDetailResponse getMoimDetail(final Long moimId) {
        Moim moim = moimRepository.findById(moimId)
                .orElseThrow(() -> new CustomException(ErrorCode.MOIM_NOT_FOUND));
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


}
