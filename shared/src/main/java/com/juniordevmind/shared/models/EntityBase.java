package com.juniordevmind.shared.models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@MappedSuperclass
public class EntityBase {
    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        if (Objects.isNull(this.id))
            this.id = UUID.randomUUID();
        if (Objects.isNull(this.createdAt))
            this.createdAt = LocalDateTime.now();
        if (Objects.isNull(this.updatedAt))
            this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
