package com.juniordevmind.bookapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.juniordevmind.shared.models.EntityBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class Book extends EntityBase {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
}
