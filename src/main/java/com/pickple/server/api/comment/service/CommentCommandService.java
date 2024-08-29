package com.pickple.server.api.comment.service;

import com.pickple.server.api.comment.domain.Comment;
import com.pickple.server.api.comment.dto.request.CommentCreateRequest;
import com.pickple.server.api.comment.repository.CommentRepository;
import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.api.notice.repository.NoticeRepository;
import com.pickple.server.api.user.domain.User;
import com.pickple.server.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandService {

    private final CommentRepository commentRepository;
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    public void createComment(Long userId,
                              Long noticeId,
                              CommentCreateRequest commentCreateRequest) {
        User user = userRepository.findUserByIdOrThrow(userId);
        Notice notice = noticeRepository.findNoticeByIdOrThrow(noticeId);

        Comment comment = Comment.builder()
                .notice(notice)
                .commenter(user)
                .commentContent(commentCreateRequest.commentContent())
                .build();

        commentRepository.save(comment);
    }
}
