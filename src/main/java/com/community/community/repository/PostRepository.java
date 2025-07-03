package com.community.community.repository;

import com.community.community.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 필요하면 findByCategory … 등 추가
}
