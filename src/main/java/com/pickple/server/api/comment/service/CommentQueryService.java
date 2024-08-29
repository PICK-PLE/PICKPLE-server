package com.pickple.server.api.comment.service;

import com.pickple.server.api.comment.domain.Comment;
import com.pickple.server.api.comment.dto.response.CommentGetResponse;
import com.pickple.server.api.comment.repository.CommentRepository;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.api.notice.repository.NoticeRepository;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQueryService {
    private final NoticeRepository noticeRepository;
    private final CommentRepository commentRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;

    public List<CommentGetResponse> getCommentListByNotice(Long noticeId) {
        Notice notice = noticeRepository.findNoticeByIdOrThrow(noticeId);
        List<Comment> commentList = commentRepository.findCommentsByNoticeId(noticeId);

        return commentList.stream().map(comment -> CommentGetResponse.builder()
                        .isOwner(checkOwner(comment.getCommenter().getId(), noticeId))
                        .commenterImageUrl(getCommenterImageUrl(comment.getCommenter().getId(),
                                checkOwner(comment.getCommenter().getId(), noticeId)))
                        .commenterNickname(getCommenterNickname(comment.getCommenter().getId(),
                                checkOwner(comment.getCommenter().getId(), noticeId)))
                        .commentContent(comment.getCommentContent())
                        .commentDate(comment.getCommentContent().formatted(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                        .build())
                .collect(Collectors.toList());
    }

    public String getCommenterNickname(Long userId, boolean isOwner) {
        if (isOwner) {
            return hostRepository.findHostByUserId(userId).getNickname();
        } else {
            return guestRepository.findGuestByUserId(userId).getNickname();
        }
    }

    public String getCommenterImageUrl(Long userId, boolean isOwner) {
        if (isOwner) {
            return hostRepository.findHostByUserId(userId).getImageUrl();
        } else {
            return guestRepository.findGuestByUserId(userId).getImageUrl();
        }
    }

    public boolean checkOwner(Long userId, Long noticeId) {
        Notice notice = noticeRepository.findNoticeByIdOrThrow(noticeId);
        return notice.getMoim().getHost().getUser().getId().equals(userId);
    }
}
