// ────────────────── TagRepository.java ──────────────────
package com.community.community.repository;

import com.community.community.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}