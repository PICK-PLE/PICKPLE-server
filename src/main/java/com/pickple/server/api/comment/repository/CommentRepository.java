package com.pickple.server.api.comment.repository;

import com.pickple.server.api.comment.domain.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByNoticeId(Long noticeId);
}
