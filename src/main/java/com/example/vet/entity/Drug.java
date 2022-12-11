package com.example.vet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drug")
@Getter
@Setter
public class Drug {
    @Id
    @Column(name="drug_code")
    private int DrugCode;

    @Column(name = "drug_name")
    private String DrugName;

    @Column(name = "drug_dosage")
    private String DrugDosage;

    @Column(name = "drug_manufacturer")
    private String DrugManufacturer;

    @Column(name = "drug_contraindications")
    private String DrugContraindications;

    @Column(name = "drug_use_time")
    private String DrugUseTime;

    @Column(name = "drug_side_effects")
    private String DrugSideEffects;

    public Drug() {
    }

    public Drug(String DrugName, String DrugDosage, String DrugManufacturer, String DrugContraindications, String DrugUseTime, String DrugSideEffects) {
        this.DrugName = DrugName;
        this.DrugDosage = DrugDosage;
        this.DrugManufacturer = DrugManufacturer;
        this.DrugContraindications = DrugContraindications;
        this.DrugUseTime = DrugUseTime;
        this.DrugSideEffects = DrugSideEffects;
    }

}
