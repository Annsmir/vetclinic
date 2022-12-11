package com.example.vet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;

@Entity
@Table(name="patient")
@Getter
@Setter
public class Patient {
    @Id
    @Column(name="PatientCode")
    private int PatientCode;

    @Column(name = "PatientName")
    private String PatientName;
    @Column(name = "PatientBirthday")
    private String PatientBirthday;
    @Column(name = "PatientCardNumber")
    private int PatientCardNumber;
    @Column(name = "PatientBreed")
    private String PatientBreed;
    @Column(name = "PatientSex")
    private String PatientSex;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_owner_code")
    private Owner OwnerOwnerCode;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_animal_category")
    private Animal AnimalAnimalCategory;

    public Patient() {
    }

}
