package com.juniordevmind.shared.domain;

import com.juniordevmind.shared.models.DtoBase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class AuthorEventDto extends DtoBase {
    private String name;
    private String description;
}
