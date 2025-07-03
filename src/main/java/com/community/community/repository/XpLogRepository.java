package com.community.community.repository;

import com.community.community.domain.XpLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface XpLogRepository extends JpaRepository<XpLog, Long> {

    /** 마이페이지용 – 최근 순 */
    List<XpLog> findByUserIdOrderByCreatedAtDesc(Long userId);

    /** 누적 XP 계산용 */
    long countByUserId(Long userId);
}
