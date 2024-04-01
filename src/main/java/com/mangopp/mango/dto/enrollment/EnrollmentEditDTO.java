package com.mangopp.mango.dto.enrollment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnrollmentEditDTO {

    @NotNull
    private LocalDateTime enrollmentDate;

    @NotNull
    private Boolean status;

    @NotNull
    @Min(1)
    private Integer studentId;

    @NotNull
    private List<EnrollmentDetailDTO> enrollmentDetails = new ArrayList<>();
}
