package com.olukunle.mkobo_assessment.entity;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Staff extends BaseModel implements Serializable {
    @Column(nullable = false, name = "staff_id")
    private String staffId;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "email")
    private String emailAddress;
}
