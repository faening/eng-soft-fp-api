package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public record EntityMetadata(
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt,

    @Column(name = "updated_at")
    @UpdateTimestamp
    LocalDateTime updatedAt
) implements Serializable  { }