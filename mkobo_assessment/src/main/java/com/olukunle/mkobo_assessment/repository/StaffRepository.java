package com.olukunle.mkobo_assessment.repository;

import com.olukunle.mkobo_assessment.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findStaffByEmailAddress(String emailAddress);
    Optional<Staff> findStaffByStaffId(String staffId);
}
