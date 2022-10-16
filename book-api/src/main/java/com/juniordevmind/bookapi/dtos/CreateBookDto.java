package com.juniordevmind.bookapi.dtos;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDto {
    @NotNull
    @Length(max = 50)
    private String title;

    @NotNull
    @Length(min = 1, max = 250)
    private String description;

    private List<UUID> authors;
}
