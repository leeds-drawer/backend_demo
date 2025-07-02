package com.community.community.controller;

import com.community.community.domain.Comment;
import com.community.community.domain.Post;
import com.community.community.domain.User;
import com.community.community.dto.CommentRequestDto;
import com.community.community.dto.CommentResponseDto;
import com.community.community.repository.CommentRepository;
import com.community.community.repository.PostRepository;
import com.community.community.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public CommentController(CommentRepository commentRepo,
                             PostRepository postRepo,
                             UserRepository userRepo) {
        this.commentRepo = commentRepo;
        this.postRepo    = postRepo;
        this.userRepo    = userRepo;
    }

    // 댓글 작성
    @PostMapping("/{postId}")
    public CommentResponseDto add(@PathVariable Long postId,
                                  @RequestBody CommentRequestDto dto,
                                  Principal principal) {
        Post p = postRepo.findById(postId).orElseThrow();
        User u = userRepo.findByUsername(principal.getName())
                         .orElseThrow();
        Comment c = new Comment();
        c.setPost(p);
        c.setAuthor(u);
        c.setContent(dto.getContent());
        Comment saved = commentRepo.save(c);
        return new CommentResponseDto(
            saved.getId(),
            u.getUsername(),
            saved.getContent(),
            saved.getCreatedAt().toString()
        );
    }
}
