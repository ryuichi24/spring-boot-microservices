package com.juniordevmind.authorapi.dtos;

import java.util.List;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAuthorDto {
    @Length(max = 50)
    private String name;
    @Length(max = 255)
    private String description;

    private List<UUID> books;
}
