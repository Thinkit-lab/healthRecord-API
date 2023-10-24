package com.olukunle.mkobo_assessment.repository;

import com.olukunle.mkobo_assessment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByAgeGreaterThanEqual(int age);

    List<Patient> findByLastVisitDateIsBetween(LocalDate start, LocalDate end);
}
