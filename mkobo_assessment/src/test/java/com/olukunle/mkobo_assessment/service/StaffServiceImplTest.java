package com.olukunle.mkobo_assessment.service;

import com.olukunle.mkobo_assessment.constants.ResponseStatus;
import com.olukunle.mkobo_assessment.constants.StringValues;
import com.olukunle.mkobo_assessment.entity.Staff;
import com.olukunle.mkobo_assessment.payload.BaseResponse;
import com.olukunle.mkobo_assessment.payload.request.StaffSignUpRequest;
import com.olukunle.mkobo_assessment.payload.response.StaffResponse;
import com.olukunle.mkobo_assessment.repository.StaffRepository;
import com.olukunle.mkobo_assessment.security.UUIDEncryptor;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StaffServiceImplTest {
    @MockBean
    private StaffRepository staffRepository;

    @MockBean
    private UUIDEncryptor uuidEncryptor;
    @Mock
    HttpServletRequest request;

    @Autowired
    StaffService staffService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testCreateStaff_Return_Success() {
        StaffSignUpRequest requestPayload = new StaffSignUpRequest();
        requestPayload.setEmailAddress("test@example.com");
        requestPayload.setName("Olukunle");

        String encryptedStaffId = "encryptedStaffId";

        Staff savedStaff = new Staff();
        savedStaff.setStaffId(encryptedStaffId);
        savedStaff.setName("John Doe");
        savedStaff.setEmailAddress("test@example.com");

        BaseResponse<StaffResponse> expectedResponse = new BaseResponse<>();
        expectedResponse.setStatus(ResponseStatus.SUCCESS.getStatus());
        expectedResponse.setCode(ResponseStatus.SUCCESS.getCode());
        expectedResponse.setMessage(StringValues.STAFF_RECORD_SAVED);
        expectedResponse.setData(new StaffResponse( "Olukunle", "test@example.com", encryptedStaffId));

        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost"));
        when(uuidEncryptor.encryptUUID(any())).thenReturn(encryptedStaffId);
        when(uuidEncryptor.decryptUUID(any())).thenReturn(encryptedStaffId);
        when(staffRepository.findStaffByEmailAddress(requestPayload.getEmailAddress())).thenReturn(Optional.empty());
        when(staffRepository.save(any(Staff.class))).thenReturn(savedStaff);

        BaseResponse<StaffResponse> response = staffService.createStaff(request, requestPayload);

        assertEquals(expectedResponse.getCode(), response.getCode());
        assertEquals(expectedResponse.getMessage(), response.getMessage());
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getData().getEmailAddress(), response.getData().getEmailAddress());
    }

    @Test
    public void testCreateStaff_AlreadyExists() {
        StaffSignUpRequest requestPayload = new StaffSignUpRequest();
        requestPayload.setEmailAddress("test@example.com");
        requestPayload.setName("Olukunle");

        Staff existingStaff = new Staff();
        existingStaff.setName(requestPayload.getName());
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost"));
        when(staffRepository.findStaffByEmailAddress(requestPayload.getEmailAddress())).thenReturn(Optional.of(existingStaff));

        BaseResponse<StaffResponse> response = staffService.createStaff(request, requestPayload);

        BaseResponse<StaffResponse> expectedResponse = new BaseResponse<>();
        expectedResponse.setCode(ResponseStatus.FAILED.getCode());
        expectedResponse.setStatus(ResponseStatus.FAILED.getStatus());
        expectedResponse.setMessage(ResponseStatus.RECORD_ALREADY_EXIST.getStatus());
        expectedResponse.setData(null);

        assertEquals(expectedResponse, response);
    }
}