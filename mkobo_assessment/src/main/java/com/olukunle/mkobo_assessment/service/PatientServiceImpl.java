package com.olukunle.mkobo_assessment.service;

import com.olukunle.mkobo_assessment.constants.ResponseStatus;
import com.olukunle.mkobo_assessment.constants.StringValues;
import com.olukunle.mkobo_assessment.entity.Patient;
import com.olukunle.mkobo_assessment.exceptions.GeneralException;
import com.olukunle.mkobo_assessment.payload.BaseResponse;
import com.olukunle.mkobo_assessment.payload.response.PatientResponse;
import com.olukunle.mkobo_assessment.repository.PatientRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public BaseResponse<List<PatientResponse>> getPatientWithAgeUpTo2Years(HttpServletRequest request, int age) {
        BaseResponse<List<PatientResponse>> response;
        String url = request.getRequestURL().toString();

        List<Patient> patients = patientRepository.findAllByAgeGreaterThanEqual(age);
        log.info("List of patient {}", patients);
        if (patients.isEmpty()) {
            response = new BaseResponse<>();
            response.setStatus(ResponseStatus.FAILED.getStatus());
            response.setCode(ResponseStatus.FAILED.getCode());
            response.setMessage(StringValues.NO_PATIENT_RECORD_FOUND);
            response.setData(new ArrayList<>());
            log.info(StringValues.LOGGER_STRING_POST, url, age, response);
            return response;
        }

        List<PatientResponse> patientResponses = patients.stream().map(patient -> {
            PatientResponse patientResponse = new PatientResponse();
            BeanUtils.copyProperties(patient, patientResponse);
            return patientResponse;
        }).toList();

        response = new BaseResponse<>();
        response.setStatus(ResponseStatus.SUCCESS.getStatus());
        response.setCode(ResponseStatus.SUCCESS.getCode());
        response.setMessage(StringValues.RECORD_FOUND);
        response.setData(patientResponses);
        log.info(StringValues.LOGGER_STRING_POST, url, age, response);
        return response;
    }

    @Override
    public BaseResponse<PatientResponse> getAndDownloadPatientProfile(HttpServletRequest request, HttpServletResponse servletResponse, Long id) {
        BaseResponse<PatientResponse> response;
        String url = request.getRequestURL().toString();

        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            response = new BaseResponse<>();
            response.setStatus(ResponseStatus.FAILED.getStatus());
            response.setCode(ResponseStatus.FAILED.getCode());
            response.setMessage(StringValues.NO_PATIENT_RECORD_FOUND_WITH_ID + id);
            response.setData(null);
            log.info(StringValues.LOGGER_STRING_GET, url, response);
            return response;
        }

        Patient patient = optionalPatient.get();
        PatientResponse patientResponse = new PatientResponse();
        BeanUtils.copyProperties(patient, patientResponse);

        try {
            servletResponse.setHeader("Content-Disposition", "attachment; filename=patient_profile.csv");
            servletResponse.setContentType("text/csv");
            CSVWriter csvWriter = new CSVWriter(servletResponse.getWriter());

            String[] headers = {"ID", "Name", "Age", "Last Visit Date"};
            csvWriter.writeNext(headers);

            String[] patientData = {
                    patient.getId().toString(),
                    patient.getName(),
                    String.valueOf(patient.getAge()),
                    patient.getLastVisitDate().toString()
            };

            csvWriter.writeNext(patientData);
            csvWriter.close();
        } catch (Exception e) {
            throw new GeneralException(ResponseStatus.FAILED.getCode(), StringValues.FAILED_WRITING_PROFILE_AS_CSV);
        }

        response = new BaseResponse<>();
        response.setStatus(ResponseStatus.SUCCESS.getStatus());
        response.setCode(ResponseStatus.SUCCESS.getCode());
        response.setMessage(StringValues.SUCCESS_WRITING_PROFILE_AS_CSV);
        response.setData(patientResponse);
        log.info(StringValues.LOGGER_STRING_POST, url, id, response);
        return response;
    }

    @Override
    public BaseResponse<String> deleteMultiplePatientProfileBetweenDateRange(HttpServletRequest request, String startDate, String endDate) {
        BaseResponse<String> response;
        String url = request.getRequestURL().toString();
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        log.info("startDate = {}, endDate = {}", start, end);

        List<Patient> patientsToDelete = patientRepository.findByLastVisitDateIsBetween(start, end);
        if (patientsToDelete.isEmpty()) {
            response = new BaseResponse<>();
            response.setStatus(ResponseStatus.FAILED.getStatus());
            response.setCode(ResponseStatus.FAILED.getCode());
            response.setMessage(StringValues.NO_PATIENT_RECORD_FOUND);
            response.setData(startDate + "-" + endDate);
            log.info(StringValues.LOGGER_STRING_GET, url, response);
            return response;
        }

        patientRepository.deleteAll(patientsToDelete);

        response = new BaseResponse<>();
        response.setStatus(ResponseStatus.SUCCESS.getStatus());
        response.setCode(ResponseStatus.SUCCESS.getCode());
        response.setMessage(StringValues.SUCCESSFUL_RECORD_DELETE);
        response.setData("Records Deleted");
        log.info(StringValues.LOGGER_STRING_GET, url, response);
        return response;
    }
}
