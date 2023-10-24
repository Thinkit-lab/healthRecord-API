package com.olukunle.mkobo_assessment.controller;

import com.olukunle.mkobo_assessment.payload.BaseResponse;
import com.olukunle.mkobo_assessment.payload.response.PatientResponse;
import com.olukunle.mkobo_assessment.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@Slf4j
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @Operation(summary = "Fetch patient profile with age upto 2 years", description = "Fetch patient profile with age upto 2 years")
    @GetMapping(value = "/fetch_patient_profile_withAgeUpto2Years", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<List<PatientResponse>>> getPatientWithAgeUpTo2Years(
            HttpServletRequest request, @RequestParam(defaultValue = "2", name = "age") int age) {
        return ResponseEntity.ok(patientService.getPatientWithAgeUpTo2Years(request, age));
    }

    @Operation(summary = "Download patient profile into csv file", description = "Download patient profile into csv file")
    @GetMapping(value = "/download_patient_profile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<PatientResponse>> getAndDownloadPatientProfile(
            HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        return ResponseEntity.ok(patientService.getAndDownloadPatientProfile(request, response, id));
    }

    @Operation(summary = "Delete multiple patient profile between 2 date range", description = "Delete multiple patient profile between 2 date range")
    @DeleteMapping(value = "/delete_multiple_patient_profile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<String>> deleteMultiplePatientProfileBetweenDateRange(
            HttpServletRequest request, @RequestParam(name = "start") String startDate, @RequestParam(name = "end") String endDate) {
        return ResponseEntity.ok(patientService.deleteMultiplePatientProfileBetweenDateRange(request, startDate, endDate));
    }
}
