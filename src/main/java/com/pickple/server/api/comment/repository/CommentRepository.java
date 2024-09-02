package com.pickple.server.api.comment.repository;

import com.pickple.server.api.comment.domain.Comment;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByNoticeId(Long noticeId);

    int countCommentByNoticeId(Long noticeId);

    Optional<Comment> findCommentById(Long id);

    default Comment findCommentByIdOrThrow(Long id) {
        return findCommentById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
