package com.juniordevmind.authorapi.models;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;

    @PrePersist
    protected void prePersist() {
        if(Objects.isNull(this.id)) {
            this.id = UUID.randomUUID();
        }
    }
}
