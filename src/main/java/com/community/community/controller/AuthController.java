package com.community.community.controller;

import com.community.community.domain.Level;
import com.community.community.domain.User;
import com.community.community.dto.LoginRequest;
import com.community.community.dto.JwtResponse;
import com.community.community.dto.SignupRequest;
import com.community.community.repository.LevelRepository;
import com.community.community.repository.UserRepository;
import com.community.community.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final LevelRepository levelRepo;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    public AuthController(AuthenticationManager authManager,
                          UserRepository userRepo,
                          LevelRepository levelRepo,
                          JwtUtils jwtUtils,
                          PasswordEncoder encoder) {
        this.authManager = authManager;
        this.userRepo    = userRepo;
        this.levelRepo   = levelRepo;
        this.jwtUtils    = jwtUtils;
        this.encoder     = encoder;
    }

    /** 회원가입 */
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest req) {
        if (userRepo.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("이미 존재하는 사용자명입니다.");
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(encoder.encode(req.getPassword()));
        userRepo.save(u);

        // 레벨 레코드도 생성
        levelRepo.save(new Level(u));
        return ResponseEntity.ok("가입 완료!");
    }

    /** 로그인 */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest req) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        String token = jwtUtils.generateJwtToken(req.getUsername());
        return ResponseEntity.ok(new JwtResponse(token, req.getUsername()));
    }
}
