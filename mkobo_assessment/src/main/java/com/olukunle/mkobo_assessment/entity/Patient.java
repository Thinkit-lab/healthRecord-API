package com.olukunle.mkobo_assessment.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
@ToString
@Entity
@Table(name = "PATIENT")
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends BaseModel implements Serializable {
    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "age")
    private int age;

    @Column(nullable = false, name = "last_visit_date")
    private LocalDate lastVisitDate;
}
