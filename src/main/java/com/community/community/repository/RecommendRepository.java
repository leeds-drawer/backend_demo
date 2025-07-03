package com.community.community.repository;

import com.community.community.domain.Recommend;
import com.community.community.domain.ParentType;
import com.community.community.domain.RecommendType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {

    /** 같은 사용자가 중복 추천했는지 체크 */
    boolean existsByUserIdAndParentTypeAndParentId(
            Long userId, ParentType parentType, Long parentId);

    /** 게시물별 추천/비추천 카운트 */
    long countByParentTypeAndParentIdAndType(
            ParentType parentType, Long parentId, RecommendType type);
}
