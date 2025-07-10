package com.community.community.controller;

import com.community.community.domain.*;
import com.community.community.dto.PostRequestDto;
import com.community.community.dto.PostResponseDto;
import com.community.community.repository.*;
import com.community.community.service.LevelService;
import org.springframework.web.bind.annotation.*;
import com.community.community.repository.PostRepository; // ← 추가

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController @RequestMapping("/posts")
public class PostController {

    private final PostRepository     postRepo;
    private final ProblemRepository  problemRepo;
    private final TagRepository      tagRepo;
    private final PostTagRepository  postTagRepo;
    private final LikeRepository     likeRepo;
    private final UserRepository     userRepo;
    private final LevelService       levelSvc;

    public PostController(PostRepository postRepo,
                          ProblemRepository problemRepo,
                          TagRepository tagRepo,
                          PostTagRepository postTagRepo,
                          LikeRepository likeRepo,
                          UserRepository userRepo,
                          LevelService levelSvc) {
        this.postRepo     = postRepo;
        this.problemRepo  = problemRepo;
        this.tagRepo      = tagRepo;
        this.postTagRepo  = postTagRepo;
        this.likeRepo     = likeRepo;
        this.userRepo     = userRepo;
        this.levelSvc     = levelSvc;
    }

    /** 글 작성 */
    @PostMapping
    public PostResponseDto create(@RequestBody PostRequestDto dto,
                                  Principal principal) {

        User author = userRepo.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));

        // 카테고리별 추가 로직
        Problem problem = null;
        if (dto.getCategory() == PostCategory.CODING_TEST) {
            if (dto.getProblemId() == null)
                throw new IllegalArgumentException("problemId 필수입니다.");
            long num = dto.getProblemId();
            problem = problemRepo.findByNumber(num)
                                 .orElseGet(() -> problemRepo.save(new Problem(num)));
        }

        Post post = Post.builder()
                .author(author)
                .category(dto.getCategory())
                .problem(problem)
                .title(dto.getTitle())
                .code(dto.getCode())
                .explanation(dto.getExplanation())
                .build();
        Post saved = postRepo.save(post);

        // 태그 처리
        if (dto.getTags() != null) {
            for (String tName : dto.getTags()) {
                Tag tag = tagRepo.findByName(tName)
                                 .orElseGet(() -> tagRepo.save(new Tag(tName)));
                postTagRepo.save(new PostTag(saved, tag));
            }
        }

        // 경험치 +10 (글 1개 = 10EXP)
        levelSvc.addExp(author, 10);

        return toDto(saved);
    }

    /** 게시물 목록 (+필터) */
    @GetMapping
    public List<PostResponseDto> list(@RequestParam(required = false) PostCategory category,
                                      @RequestParam(required = false) Long problemId,
                                      @RequestParam(required = false) String tag) {

        List<Post> base = postRepo.findAll();

        if (category != null)
            base = base.stream().filter(p -> p.getCategory() == category).toList();

        if (problemId != null)
            base = base.stream()
                       .filter(p -> p.getProblem() != null &&
                                    p.getProblem().getNumber() == problemId)
                       .toList();

        if (tag != null) {
            Tag tagEnt = tagRepo.findByName(tag).orElse(null);
            if (tagEnt == null) return List.of(); // 해당 태그 없음
            Set<Long> postIds = postTagRepo.findByTag(tagEnt)
                                           .stream()
                                           .map(pt -> pt.getPost().getId())
                                           .collect(Collectors.toSet());
            base = base.stream().filter(p -> postIds.contains(p.getId())).toList();
        }

        return base.stream().map(this::toDto).toList();
    }

    /** 좋아요 토글 */
    @PostMapping("/{postId}/like")
    public Map<String, Object> toggleLike(@PathVariable Long postId,
                                          Principal principal) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepo.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));

        likeRepo.findByPostAndUser(post, user).ifPresentOrElse(
            likeRepo::delete,
            () -> {
                likeRepo.save(new Like(post, user));
                // 글쓴이 EXP +1
                levelSvc.addExp(post.getAuthor(), 1);
            }
        );
        long cnt = likeRepo.countByPost(post);
        return Map.of("postId", postId, "likeCount", cnt);
    }

    /* ---------- 내부 util ---------- */
    private PostResponseDto toDto(Post p) {
        List<String> tags = postTagRepo.findByPost(p).stream()
                                       .map(pt -> pt.getTag().getName())
                                       .toList();
        return new PostResponseDto(
                p.getId(),
                p.getAuthor().getUsername(),
                p.getCategory(),
                p.getProblem() == null ? null : p.getProblem().getNumber(),
                p.getTitle(),
                p.getCode(),
                p.getExplanation(),
                p.getCreatedAt().toString(),
                likeRepo.countByPost(p),
                tags
        );
    }
}
