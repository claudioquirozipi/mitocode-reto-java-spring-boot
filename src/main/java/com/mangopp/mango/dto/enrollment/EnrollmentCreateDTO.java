package com.mangopp.mango.dto.enrollment;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnrollmentCreateDTO {

    @NotNull
    private LocalDateTime enrollmentDate;

    @NotNull
    private Boolean status;

    @NotNull
    private Integer studentId;

    @NotNull
    private List<EnrollmentDetailDTO> enrollmentDetails = new ArrayList<>();

}
