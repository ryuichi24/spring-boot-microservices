package com.juniordevmind.bookapi.dtos;

import com.juniordevmind.shared.models.DtoBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class BookDto extends DtoBase {
    private String title;
    private String description;
}
