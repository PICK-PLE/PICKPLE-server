package com.pickple.server.api.comment.service;

import com.pickple.server.api.comment.domain.Comment;
import com.pickple.server.api.comment.dto.request.CommentCreateRequest;
import com.pickple.server.api.comment.repository.CommentRepository;
import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.api.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandService {

    private final CommentRepository commentRepository;
    private final NoticeRepository noticeRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;

    public void createComment(Long guestId,
                              Long hostId,
                              Long noticeId,
                              CommentCreateRequest commentCreateRequest) {
        Notice notice = noticeRepository.findNoticeByIdOrThrow(noticeId);
        if (notice.getMoim().getHost().getId().equals(hostId)) {
            Host host = hostRepository.findHostByIdOrThrow(hostId);
            Comment comment = Comment.builder()
                    .notice(notice)
                    .commenterImageUrl(host.getImageUrl())
                    .commenter(host.getUser())
                    .commentContent(commentCreateRequest.commentContent())
                    .isOwner(true)
                    .build();
            commentRepository.save(comment);
        } else {
            Guest guest = guestRepository.findGuestByIdOrThrow(guestId);
            Comment comment = Comment.builder()
                    .notice(notice)
                    .commenterImageUrl(guest.getImageUrl())
                    .commenter(guest.getUser())
                    .commentContent(commentCreateRequest.commentContent())
                    .isOwner(false)
                    .build();
            commentRepository.save(comment);
        }
    }
}
