package com.pickple.server.api.notice.service;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.api.notice.dto.request.NoticeCreateRequest;
import com.pickple.server.api.notice.repository.NoticeRepository;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeCommandService {

    private final MoimRepository moimRepository;
    private final NoticeRepository noticeRepository;

    public void createNotice(Long moimId, NoticeCreateRequest request) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);

        Notice notice = Notice.builder()
                .title(request.noticeTitle())
                .content(request.noticeContent())
                .imageUrl(request.imageUrl())
                .moim(moim)
                .isPrivate(request.isPrivate())
                .build();

        noticeRepository.save(notice);
    }

    public void deleteNotice(Long hostId, Long noticeId) {
        Notice notice = noticeRepository.findNoticeByIdOrThrow(noticeId);
        if (!hostId.equals(notice.getMoim().getHost().getId())) {
            throw new CustomException(ErrorCode.NOT_HOST);
        }
        noticeRepository.delete(notice);
    }
}
