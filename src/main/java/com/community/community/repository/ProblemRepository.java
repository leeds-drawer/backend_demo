// ────────────────── ProblemRepository.java ──────────────────
package com.community.community.repository;

import com.community.community.domain.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    Optional<Problem> findByNumber(long number);
}