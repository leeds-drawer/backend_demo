package com.community.community.repository;

import com.community.community.domain.InterviewPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewPostRepository extends JpaRepository<InterviewPost, Long> {

    /** 자유 태그 포함 검색 */
    List<InterviewPost> findByTagsContaining(String tag);

    /** 추천 Top-10 */
    List<InterviewPost> findTop10ByOrderByLikeCntDescCreatedAtDesc();
}
