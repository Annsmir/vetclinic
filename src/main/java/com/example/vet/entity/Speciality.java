package com.example.vet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "speciality")
@Getter
@Setter
public class Speciality {
    @Id
    @Column(name="speciality_code")
    private int SpecialityCode;

    @Column(name = "speciality_name")
    private String SpecialityName;

    public Speciality() {
    }

    public Speciality(String SpecialityName) {
        this.SpecialityName = SpecialityName;
    }

}
