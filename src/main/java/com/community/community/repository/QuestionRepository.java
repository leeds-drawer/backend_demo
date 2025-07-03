package com.community.community.repository;

import com.community.community.domain.Question;
import com.community.community.domain.Problem;
import com.community.community.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    long countByProblem(Problem problem);
    List<Question> findByUser(User user);
}