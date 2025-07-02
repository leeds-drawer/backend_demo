package com.community.community.repository;
import com.community.community.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 예: findByPostId 같은 메서드를 정의해도 됨
}