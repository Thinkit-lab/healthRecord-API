package com.olukunle.mkobo_assessment.payload.response;

import com.olukunle.mkobo_assessment.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponse {
    private Long id;
    private String name;
    private int age;
    private LocalDate lastVisitDate;
}
