package com.olukunle.mkobo_assessment.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStaffRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
