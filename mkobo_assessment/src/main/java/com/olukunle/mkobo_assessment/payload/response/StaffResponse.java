package com.olukunle.mkobo_assessment.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffResponse {
    private String name;
    private String emailAddress;
    private String staffId;
}
