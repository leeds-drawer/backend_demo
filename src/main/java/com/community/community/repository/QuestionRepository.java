package com.community.community.repository;

import com.community.community.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    /** 문제 번호별 Question 목록 */
    List<Question> findByProblemNoOrderByCreatedAtDesc(int problemNo);

    /** 문제 번호별 누적 질문 개수 */
    long countByProblemNo(int problemNo);
}
