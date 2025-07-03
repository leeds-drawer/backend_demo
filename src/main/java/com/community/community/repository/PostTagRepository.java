// ────────────────── PostTagRepository.java ──────────────────
package com.community.community.repository;

import com.community.community.domain.Post;
import com.community.community.domain.PostTag;
import com.community.community.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    List<PostTag> findByPost(Post post);
    List<PostTag> findByTag(Tag tag);
}