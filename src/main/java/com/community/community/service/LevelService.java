package com.community.community.service;

import com.community.community.domain.Level;
import com.community.community.domain.User;
import com.community.community.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class LevelService {

    private final LevelRepository levelRepo;

    @Transactional
    public void addExp(User user, long amount) {
        Level lv = levelRepo.findByUser(user)
                .orElseGet(() -> levelRepo.save(new Level(user)));
        lv.gain(amount);
    }
}
