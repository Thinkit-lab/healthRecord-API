package com.olukunle.mkobo_assessment.service;

import com.olukunle.mkobo_assessment.payload.BaseResponse;
import com.olukunle.mkobo_assessment.payload.response.PatientResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PatientService {
    BaseResponse<List<PatientResponse>> getPatientWithAgeUpTo2Years(HttpServletRequest request, int age);

    BaseResponse<PatientResponse> getAndDownloadPatientProfile(HttpServletRequest request, HttpServletResponse servletResponse, Long id);

    BaseResponse<String> deleteMultiplePatientProfileBetweenDateRange(HttpServletRequest request, String startDate, String endDate);
}
