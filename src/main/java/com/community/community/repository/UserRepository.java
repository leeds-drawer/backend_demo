package com.community.community.repository;
import com.community.community.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // username 으로 회원 조회할 수 있는 메서드 서명
    Optional<User> findByUsername(String username);
}
