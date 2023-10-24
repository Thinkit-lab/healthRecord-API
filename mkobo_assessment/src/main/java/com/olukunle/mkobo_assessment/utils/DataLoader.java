package com.olukunle.mkobo_assessment.utils;

import com.olukunle.mkobo_assessment.entity.Patient;
import com.olukunle.mkobo_assessment.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final PatientRepository patientRepository;

    public void loadInitialData() {
        Random random = new Random();

        for (int i = 1; i <= 100; i++) {
            int randomAge = random.nextInt(90) + 1;
            int randomNum = random.nextInt(10);
            Patient patient = new Patient();
            patient.setName("Patient " + i);
            patient.setAge(randomAge);
            patient.setLastVisitDate(LocalDate.now().minusDays(randomNum));
            patientRepository.save(patient);
        }
    }
}





