package com.olukunle.mkobo_assessment.controller;

import com.olukunle.mkobo_assessment.payload.BaseResponse;
import com.olukunle.mkobo_assessment.payload.request.StaffSignUpRequest;
import com.olukunle.mkobo_assessment.payload.request.UpdateStaffRequest;
import com.olukunle.mkobo_assessment.payload.response.StaffResponse;
import com.olukunle.mkobo_assessment.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    @Operation(summary = "Create new staff", description = "Create new staff")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<StaffResponse>> createStaff(
            HttpServletRequest request, @Valid @RequestBody StaffSignUpRequest requestPayload) {
        return ResponseEntity.ok(staffService.createStaff(request, requestPayload));
    }

    @Operation(summary = "Update staff profile", description = "Update staff profile")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<StaffResponse>> updateStaff(
            HttpServletRequest request, @Valid @RequestBody UpdateStaffRequest requestPayload) {
        return ResponseEntity.ok(staffService.updateStaff(request, requestPayload));
    }
}
