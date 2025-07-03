// ────────────────── LevelRepository.java ──────────────────
package com.community.community.repository;

import com.community.community.domain.Level;
import com.community.community.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Long> {
    Optional<Level> findByUser(User user);
}