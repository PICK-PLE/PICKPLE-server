package com.pickple.server.api.notice.repository;

import com.pickple.server.api.notice.domain.Notice;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT DISTINCT n FROM Notice n JOIN FETCH n.moim m JOIN FETCH m.host "
            + "LEFT JOIN FETCH n.comments WHERE m.id = :moimId ORDER BY n.createdAt DESC")
    List<Notice> findNoticesByMoimId(Long moimId);

    @Query("SELECT DISTINCT n FROM Notice n JOIN FETCH n.moim m JOIN FETCH m.host "
            + "LEFT JOIN FETCH n.comments WHERE n.id = :id ")
    Optional<Notice> findNoticeById(Long id);
    
    default Notice findNoticeByIdOrThrow(Long id) {
        return findNoticeById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));
    }
}
