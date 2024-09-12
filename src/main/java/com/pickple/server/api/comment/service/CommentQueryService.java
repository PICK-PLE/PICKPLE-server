package com.pickple.server.api.comment.service;

import com.pickple.server.api.comment.domain.Comment;
import com.pickple.server.api.comment.domain.CommenterInfo;
import com.pickple.server.api.comment.dto.response.CommentGetResponse;
import com.pickple.server.api.comment.repository.CommentRepository;
import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.host.domain.Host;
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
        List<Comment> commentList = commentRepository.findCommentsByNoticeId(notice.getId());
        return commentList.stream().map(comment -> {
                    boolean isOwner = checkOwner(comment.getCommenter().getId(), noticeId);
                    return buildCommentGetResponse(comment, checkOwner(comment.getCommenter().getId(), noticeId),
                            getCommenterInfo(comment.getCommenter().getId(), isOwner));
                })
                .collect(Collectors.toList());
    }

    private CommentGetResponse buildCommentGetResponse(Comment comment, boolean isOwner, CommenterInfo commenterInfo) {
        return CommentGetResponse.builder()
                .commentId(comment.getId())
                .isOwner(isOwner)
                .commenterImageUrl(commenterInfo.getProfileImageUrl())
                .commenterNickname(commenterInfo.getProfileNickname())
                .commentContent(comment.getCommentContent())
                .commentDate(comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();
    }

    public CommenterInfo getCommenterInfo(Long userId, boolean isOwner) {
        if (isOwner) {
            Host host = hostRepository.findHostByUserId(userId);
            return new CommenterInfo(host.getImageUrl(), host.getNickname());
        } else {
            Guest guest = guestRepository.findGuestByUserId(userId);
            return new CommenterInfo(guest.getImageUrl(), guest.getNickname());
        }
    }

    public boolean checkOwner(Long userId, Long noticeId) {
        Notice notice = noticeRepository.findNoticeByIdOrThrow(noticeId);
        return notice.getMoim().getHost().getUser().getId().equals(userId);
    }
}
