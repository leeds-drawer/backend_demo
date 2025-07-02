package com.community.community.repository;
import com.community.community.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 필요하다면 커스텀 메서드 추가 가능 (예: findByAuthor…)
}