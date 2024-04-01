package com.mangopp.mango.dto.course;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CourseEditDTO {

    @NotNull
    private String name;

    @NotNull
    private String acronym;

    @NotNull
    private Boolean status;

}
