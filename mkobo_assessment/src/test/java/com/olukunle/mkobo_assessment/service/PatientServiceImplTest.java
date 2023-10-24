package com.olukunle.mkobo_assessment.service;

import com.olukunle.mkobo_assessment.constants.ResponseStatus;
import com.olukunle.mkobo_assessment.constants.StringValues;
import com.olukunle.mkobo_assessment.entity.Patient;
import com.olukunle.mkobo_assessment.payload.BaseResponse;
import com.olukunle.mkobo_assessment.payload.response.PatientResponse;
import com.olukunle.mkobo_assessment.repository.PatientRepository;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PatientServiceImplTest {

    @MockBean
    private PatientRepository patientRepository;
    @Mock
    HttpServletRequest request;

    @Autowired
    PatientService patientService;

    @Test
    public void testGetPatientWithAgeUpTo2YearsSuccess() {
        int age = 2;
        List<Patient> patients = new ArrayList<>();
        patients.add(createPatient("Alice", 4, LocalDate.now()));
        patients.add(createPatient("Bob", 3, LocalDate.now()));
        patients.add(createPatient("Carol", 3, LocalDate.now()));

        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost"));
        when(patientRepository.findAllByAgeGreaterThanEqual(age)).thenReturn(patients);

        BaseResponse<List<PatientResponse>> response = patientService.getPatientWithAgeUpTo2Years(request, age);

        assertEquals(ResponseStatus.SUCCESS.getStatus(), response.getStatus());
        assertEquals(ResponseStatus.SUCCESS.getCode(), response.getCode());
        assertEquals(StringValues.RECORD_FOUND, response.getMessage());
        assertEquals(3, response.getData().size());
    }

    @Test
    public void testGetPatientWithAgeUpTo2Years_Return_NoPatientsFound() {
        int age = 2;
        List<Patient> patients = new ArrayList<>();

        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost"));
        when(patientRepository.findAllByAgeGreaterThanEqual(age)).thenReturn(patients);

        BaseResponse<List<PatientResponse>> response = patientService.getPatientWithAgeUpTo2Years(request, age);

        assertEquals(ResponseStatus.FAILED.getStatus(), response.getStatus());
        assertEquals(ResponseStatus.FAILED.getCode(), response.getCode());
        assertEquals(StringValues.NO_PATIENT_RECORD_FOUND, response.getMessage());
        assertEquals(0, response.getData().size()); // Expecting an empty list in the response
    }

    private Patient createPatient(String name, int age, LocalDate lastVisit) {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patient.setLastVisitDate(lastVisit);
        return patient;
    }
}