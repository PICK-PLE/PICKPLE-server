package com.pickple.server.api.notice.repository;

import com.pickple.server.api.notice.domain.Notice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findNoticeByMoimIdOrderByCreatedAtDesc(Long moimId);
}
