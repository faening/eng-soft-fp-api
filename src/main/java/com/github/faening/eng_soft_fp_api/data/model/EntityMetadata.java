package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

@Embeddable
public record EntityMetadata(
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt,

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    Date updatedAt
) implements Serializable  { }