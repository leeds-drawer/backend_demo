// ────────────────── domain/BaseTimeEntity.java ──────────────────
package com.community.community.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

/** 공통 생성‧수정 시각 */
@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    @Column(updatable = false, nullable = false)
    protected LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    protected LocalDateTime updatedAt = LocalDateTime.now();
}