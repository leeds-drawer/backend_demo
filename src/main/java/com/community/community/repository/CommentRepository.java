package com.community.community.repository;

import com.community.community.domain.Comment;
import com.community.community.domain.ParentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /** 특정 게시물(종류+PK) 의 댓글 목록 */
    List<Comment> findByParentTypeAndParentIdOrderByCreatedAtAsc(
            ParentType parentType, Long parentId);

    /** 댓글 개수(목록 집계용) */
    long countByParentTypeAndParentId(ParentType parentType, Long parentId);
}
