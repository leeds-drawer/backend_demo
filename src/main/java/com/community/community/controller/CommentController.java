package com.community.community.controller;

import com.community.community.domain.Comment;
import com.community.community.domain.Post;
import com.community.community.domain.User;
import com.community.community.dto.CommentRequestDto;
import com.community.community.dto.CommentResponseDto;
import com.community.community.repository.CommentRepository;
import com.community.community.repository.PostRepository;
import com.community.community.repository.UserRepository;
import com.community.community.service.LevelService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController @RequestMapping("/comments")
public class CommentController {

    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final LevelService   levelSvc;

    public CommentController(CommentRepository commentRepo,
                             PostRepository postRepo,
                             UserRepository userRepo,
                             LevelService levelSvc) {
        this.commentRepo = commentRepo;
        this.postRepo    = postRepo;
        this.userRepo    = userRepo;
        this.levelSvc    = levelSvc;
    }

    /** 댓글 작성 */
    @PostMapping("/{postId}")
    public CommentResponseDto add(@PathVariable Long postId,
                                  @RequestBody CommentRequestDto dto,
                                  Principal principal) {
        Post p = postRepo.findById(postId).orElseThrow();
        User u = userRepo.findByUsername(principal.getName()).orElseThrow();

        Comment c = new Comment();
        c.setPost(p); c.setAuthor(u); c.setContent(dto.getContent());
        Comment saved = commentRepo.save(c);

        // 댓글 1개 = EXP 2
        levelSvc.addExp(u, 2);

        return new CommentResponseDto(
            saved.getId(),
            u.getUsername(),
            saved.getContent(),
            saved.getCreatedAt().toString()
        );
    }
}
