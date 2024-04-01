package com.mangopp.mango.dto.student;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class StudentEditDTO {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String dni;

    @NotNull
    private Integer age;
}
