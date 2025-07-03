// ────────────────── LikeRepository.java ──────────────────
package com.community.community.repository;

import com.community.community.domain.Like;
import com.community.community.domain.Post;
import com.community.community.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);
    long countByPost(Post post);
}