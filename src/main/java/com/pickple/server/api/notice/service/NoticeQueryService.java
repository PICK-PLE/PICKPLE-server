package com.pickple.server.api.notice.service;

import com.pickple.server.api.comment.repository.CommentRepository;
import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.api.notice.dto.response.NoticeDetailGetResponse;
import com.pickple.server.api.notice.dto.response.NoticeListGetByMoimResponse;
import com.pickple.server.api.notice.repository.NoticeRepository;
import com.pickple.server.global.util.DateTimeUtil;
import java.time.format.DateTimeFormatter;
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
    private final CommentRepository commentRepository;

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
                        .commentNumber(commentRepository.countCommentByNoticeId(oneNotice.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    public NoticeDetailGetResponse getNoticeDetail(Long userId, Long moimId, Long noticeId) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);
        Notice notice = noticeRepository.findNoticeByIdOrThrow(noticeId);

        return NoticeDetailGetResponse.builder()
                .hostImageUrl(moim.getHost().getImageUrl())
                .hostNickname(moim.getHost().getNickname())
                .title(notice.getTitle())
                .content(notice.getContent())
                .noticeImageUrl(notice.getImageUrl())
                .dateTime(notice.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")))
                .commentNumber(commentRepository.countCommentByNoticeId(noticeId))
                .isPrivate(notice.isPrivate())
                .isOwner(checkOwner(userId, moim.getId()))
                .build();
    }

    public boolean checkOwner(Long userId, Long moimId) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);
        return moim.getHost().getUser().getId().equals(userId);
    }
}