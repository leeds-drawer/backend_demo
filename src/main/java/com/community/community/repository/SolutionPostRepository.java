package com.community.community.repository;

import com.community.community.domain.SolutionPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolutionPostRepository extends JpaRepository<SolutionPost, Long> {

    /** 백준 문제 번호로 검색 */
    List<SolutionPost> findByProblemNoOrderByCreatedAtDesc(int problemNo);

    /** 태그 포함 여부로 필터링 */
    List<SolutionPost> findByTagsContaining(String tag);

    /** 추천 수(Like) 기준 Top-N 랭킹 – JPQL로도 가능하지만 간단히 메서드 정의 */
    List<SolutionPost> findTop10ByOrderByLikeCntDescCreatedAtDesc();
}
