package com.pickple.server.api.notice.repository;

import com.pickple.server.api.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
