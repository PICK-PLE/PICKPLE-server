package com.pickple.server.api.notice.service;

import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.api.notice.dto.request.NoticeCreateRequest;
import com.pickple.server.api.notice.repository.NoticeRepository;
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
                .build();

        noticeRepository.save(notice);
    }
}
