package com.pickple.server.api.notice.service;

import com.pickple.server.api.comment.repository.CommentRepository;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.moim.domain.Moim;
import com.pickple.server.api.moim.repository.MoimRepository;
import com.pickple.server.api.moimsubmission.domain.MoimSubmission;
import com.pickple.server.api.moimsubmission.repository.MoimSubmissionRepository;
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
    private final CommentRepository commentRepository;
    private final MoimSubmissionRepository moimSubmissionRepository;
    private final GuestRepository guestRepository;

    public List<NoticeListGetByMoimResponse> getNoticeListByMoimId(Long moimId, Long guestId) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);
        List<Notice> noticeList = noticeRepository.findNoticeByMoimIdOrderByCreatedAtDesc(moimId);

        boolean isAppliedUser = isUserAppliedToMoim(moimId, guestId);

        return noticeList.stream()
                .filter(notice -> canAccessNotice(notice, isAppliedUser))
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
                .dateTime(DateTimeUtil.refineDateAndTime(notice.getCreatedAt()))
                .commentNumber(commentRepository.countCommentByNoticeId(noticeId))
                .isPrivate(notice.isPrivate())
                .isOwner(checkOwner(userId, moim.getId()))
                .build();
    }

    private boolean checkOwner(Long userId, Long moimId) {
        Moim moim = moimRepository.findMoimByIdOrThrow(moimId);
        return moim.getHost().getUser().getId().equals(userId);
    }

    private boolean isUserAppliedToMoim(Long moimId, Long guestId) {
        if ((moimRepository.findMoimByIdOrThrow(moimId).getHost().getUser())
                .equals(guestRepository.findGuestByIdOrThrow(guestId).getUser().getId())) {
            return true;
        } else if (moimSubmissionRepository.existsByMoimIdAndGuestId(moimId, guestId)) {
            MoimSubmission moimSubmission = moimSubmissionRepository.findByMoimIdAndGuestId(moimId, guestId);

            // 참가한 상태일 경우(승인된 상태 - approved , completed
            if (moimSubmission.getMoimSubmissionState().equals("completed")
                    || moimSubmission.getMoimSubmissionState().equals("approved")) {
                return true;
            }
        }
        return false;
    }

    private boolean canAccessNotice(Notice notice, boolean isUserApplied) {
        // 공개 공지사항이면 누구나 접근 가능하고, 비공개 공지사항이면 모임에 신청한 사람만 접근 가능
        return !notice.isPrivate() || isUserApplied;
    }
}