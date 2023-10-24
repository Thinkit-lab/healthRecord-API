package com.olukunle.mkobo_assessment.service;

import com.olukunle.mkobo_assessment.constants.ResponseStatus;
import com.olukunle.mkobo_assessment.constants.StringValues;
import com.olukunle.mkobo_assessment.entity.Staff;
import com.olukunle.mkobo_assessment.payload.BaseResponse;
import com.olukunle.mkobo_assessment.payload.request.StaffSignUpRequest;
import com.olukunle.mkobo_assessment.payload.request.UpdateStaffRequest;
import com.olukunle.mkobo_assessment.payload.response.StaffResponse;
import com.olukunle.mkobo_assessment.repository.StaffRepository;
import com.olukunle.mkobo_assessment.security.UUIDEncryptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final UUIDEncryptor uuidEncryptor;
    @Override
    public BaseResponse<StaffResponse> createStaff(HttpServletRequest request, StaffSignUpRequest requestPayload) {
        BaseResponse<StaffResponse> response;
        String url = request.getRequestURL().toString();

        Optional<Staff> createdStaff = staffRepository.findStaffByEmailAddress(requestPayload.getEmailAddress());
        if (createdStaff.isPresent()) {
            response = new BaseResponse<>();
            response.setCode(ResponseStatus.FAILED.getCode());
            response.setStatus(ResponseStatus.FAILED.getStatus());
            response.setMessage(ResponseStatus.RECORD_ALREADY_EXIST.getStatus());
            response.setData(null);
            log.info(StringValues.LOGGER_STRING_POST, url, requestPayload, response);
            return response;
        }
        String uuid = uuidEncryptor.encryptUUID(UUID.randomUUID().toString());

        Staff newStaff = new Staff();
        BeanUtils.copyProperties(requestPayload, newStaff);
        newStaff.setStaffId(uuid);

        Staff saveStaff = staffRepository.save(newStaff);
        log.info("saveStaff ===> {}", saveStaff);

        StaffResponse staffResponse = StaffResponse.builder()
                .staffId(uuidEncryptor.decryptUUID(saveStaff.getStaffId()))
                .name(saveStaff.getName())
                .emailAddress(saveStaff.getEmailAddress())
                .build();

        response = new BaseResponse<>();
        response.setStatus(ResponseStatus.SUCCESS.getStatus());
        response.setCode(ResponseStatus.SUCCESS.getCode());
        response.setMessage(StringValues.STAFF_RECORD_SAVED);
        response.setData(staffResponse);
        log.info(StringValues.LOGGER_STRING_POST, url, requestPayload, response);
        return response;
    }

    @Override
    public BaseResponse<StaffResponse> updateStaff(HttpServletRequest request, UpdateStaffRequest requestPayload) {
        BaseResponse<StaffResponse> response;
        String auth = request.getHeader(StringValues.AUTHORIZATION_KEY);
        String staffId = uuidEncryptor.encryptUUID(auth);
        String url = request.getRequestURL().toString();

        Optional<Staff> existingStaff = staffRepository.findStaffByStaffId(staffId);
        if (existingStaff.isEmpty()) {
            response = new BaseResponse<>();
            response.setCode(ResponseStatus.FAILED.getCode());
            response.setStatus(ResponseStatus.FAILED.getStatus());
            response.setMessage(ResponseStatus.INVALID_STAFF_ID.getStatus());
            response.setData(new StaffResponse());
            log.info(StringValues.LOGGER_STRING_POST, url, requestPayload, response);
            return response;
        }

        Staff updateStaff = existingStaff.get();
        updateStaff.setName(requestPayload.getName());
        Staff updatedStaff = staffRepository.save(updateStaff);

        StaffResponse staffResponse = StaffResponse.builder()
                .staffId(uuidEncryptor.decryptUUID(updatedStaff.getStaffId()))
                .name(updatedStaff.getName())
                .emailAddress(updatedStaff.getEmailAddress())
                .build();

        response = new BaseResponse<>();
        response.setStatus(ResponseStatus.SUCCESS.getStatus());
        response.setCode(ResponseStatus.SUCCESS.getCode());
        response.setMessage(StringValues.STAFF_RECORD_UPDATED);
        response.setData(staffResponse);
        log.info(StringValues.LOGGER_STRING_POST, url, requestPayload, response);
        return response;
    }

    @Override
    public boolean validateStaff(String staffId) {
        Optional<Staff> existingStaff = staffRepository.findStaffByStaffId(staffId);
        return existingStaff.isPresent();
    }
}
