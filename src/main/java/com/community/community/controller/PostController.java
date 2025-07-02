package com.community.community.controller;

import com.community.community.domain.Post;
import com.community.community.domain.User;
import com.community.community.dto.PostRequestDto;
import com.community.community.dto.PostResponseDto;
import com.community.community.repository.PostRepository;
import com.community.community.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public PostController(PostRepository postRepo,
                          UserRepository userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    // 글 작성
    @PostMapping
    public PostResponseDto create(@RequestBody PostRequestDto dto,
                                  Principal principal) {
        User u = userRepo.findByUsername(principal.getName())
                         .orElseThrow();
        Post p = new Post();
        p.setAuthor(u);
        p.setTitle(dto.getTitle());
        p.setContent(dto.getContent());
        Post saved = postRepo.save(p);
        return new PostResponseDto(
            saved.getId(),
            u.getUsername(),
            saved.getTitle(),
            saved.getContent(),
            saved.getCreatedAt().toString()
        );
    }

    // 전체 글 목록 조회
    @GetMapping
    public List<PostResponseDto> list() {
        return postRepo.findAll().stream()
            .map(p -> new PostResponseDto(
                p.getId(),
                p.getAuthor().getUsername(),
                p.getTitle(),
                p.getContent(),
                p.getCreatedAt().toString()
            ))
            .toList();
    }
}
