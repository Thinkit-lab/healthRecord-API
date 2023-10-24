package com.olukunle.mkobo_assessment.service;

import com.olukunle.mkobo_assessment.payload.BaseResponse;
import com.olukunle.mkobo_assessment.payload.request.StaffSignUpRequest;
import com.olukunle.mkobo_assessment.payload.request.UpdateStaffRequest;
import com.olukunle.mkobo_assessment.payload.response.StaffResponse;

import javax.servlet.http.HttpServletRequest;

public interface StaffService {
    BaseResponse<StaffResponse> createStaff(HttpServletRequest request, StaffSignUpRequest requestPayload);

    BaseResponse<StaffResponse> updateStaff(HttpServletRequest request, UpdateStaffRequest requestPayload);
    boolean validateStaff(String staffId);
}
