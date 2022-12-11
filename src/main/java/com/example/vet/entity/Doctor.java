package com.example.vet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;


@Entity
@Table(name = "doctor")
@Getter
@Setter
public class Doctor {
    @Id
    private int DoctorCode;

    @Column(name = "doctor_surname")
    private String DoctorSurname;

    @Column(name = "doctor_name")
    private String DoctorName;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "speciality_speciality_code")
    private Speciality SpecialitySpecialityCode;

    public Doctor() {
    }
}

