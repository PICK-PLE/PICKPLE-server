package com.pickple.server.api.notice.service;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.api.notice.dto.response.NoticeListGetByMoimResponse;
import com.pickple.server.api.notice.repository.NoticeRepository;
import com.pickple.server.global.util.DateTimeUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeQueryService {

    private final MoimRepository moimRepository;
    private final NoticeRepository noticeRepository;

    public List<NoticeListGetByMoimResponse> getNoticeListByMoimId(Long moimId) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);
        List<Notice> noticeList = noticeRepository.findNoticeByMoimId(moimId);

        return noticeList.stream()
                .map(oneNotice -> NoticeListGetByMoimResponse.builder()
                        .noticeId(oneNotice.getId())
                        .hostNickName(moim.getHost().getNickname())
                        .hostImageUrl(moim.getHost().getImageUrl())
                        .title(oneNotice.getTitle())
                        .content(oneNotice.getContent())
                        .date(DateTimeUtil.refineTime(oneNotice.getCreatedAt()))
                        .noticeImageUrl(oneNotice.getImageUrl())
                        .hostId(moim.getHost().getId())
                        .build())
                .collect(Collectors.toList());
    }
}