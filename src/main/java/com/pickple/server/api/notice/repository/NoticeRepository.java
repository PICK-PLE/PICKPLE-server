package com.pickple.server.api.notice.repository;

import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findNoticeByMoimIdOrderByCreatedAtDesc(Long moimId);

    Optional<Notice> findNoticeById(Long id);

    default Notice findNoticeByIdOrThrow(Long id) {
        return findNoticeById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));
    }
}
