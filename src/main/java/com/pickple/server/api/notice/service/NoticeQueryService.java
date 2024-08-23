package com.pickple.server.api.notice.service;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.api.notice.dto.response.NoticeDetailGetResponse;
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
        List<Notice> noticeList = noticeRepository.findNoticeByMoimIdOrderByCreatedAtDesc(moimId);

        return noticeList.stream()
                .map(oneNotice -> NoticeListGetByMoimResponse.builder()
                        .noticeId(oneNotice.getId())
                        .hostNickName(moim.getHost().getNickname())
                        .hostImageUrl(moim.getHost().getImageUrl())
                        .title(oneNotice.getTitle())
                        .content(oneNotice.getContent())
                        .date(DateTimeUtil.refineDateAndTime(oneNotice.getCreatedAt()))
                        .noticeImageUrl(oneNotice.getImageUrl())
                        .hostId(moim.getHost().getId())
                        .build())
                .collect(Collectors.toList());
    }

    public NoticeDetailGetResponse getNoticeDetail(Long hostId, Long moimId, Long noticeId) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);
        Notice notice = noticeRepository.findNoticeByIdOrThrow(noticeId);

        return NoticeDetailGetResponse.builder()
                .hostImageUrl(moim.getHost().getImageUrl())
                .hostNickname(moim.getHost().getNickname())
                .title(notice.getTitle())
                .content(notice.getContent())
                .dateTime(notice.getCreatedAt())
                .commentNumber(0)
                .isPrivate(notice.isPrivate())
                .isOwner(checkOwner(hostId, moimId))
                .build();
    }

    public boolean checkOwner(Long hostId, Long moimId) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);
        return hostId.equals(moim.getHost().getId());
    }
}