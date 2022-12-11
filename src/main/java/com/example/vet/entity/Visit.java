package com.example.vet.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="visit")
@Getter
@Setter
public class Visit {
    @Id
    @SequenceGenerator(
            name = "visit_sequence",
            sequenceName = "visit_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "visit_sequence"
    )
    @Column(name="VisitCode")
    private int VisitCode;

    @Column(name = "VisitDate")
    private String VisitDate;
    @Column(name = "VisitSymptoms")
    private String VisitSymptoms;
    @Column(name = "TestResults")
    private String TestResults;
    @Column(name = "VisitNumber")
    private int VisitNumber;
    @Column(name = "Patient_PatientCode")
    private int Patient_PatientCode;
    @Column(name = "Drug_DrugCode")
    private int Drug_DrugCode;
    @Column(name = "Diagnosis_DiagnosisCode")
    private int Diagnosis_DiagnosisCode;
    @Column(name = "Doctor_DoctorCode")
    private int Doctor_DoctorCode;

    public Visit() {
    }

    public Visit(String VisitDate, String VisitSymptoms, String TestResults, int VisitNumber, int Patient_PatientCode, int Drug_DrugCode, int Diagnosis_DiagnosisCode, int Doctor_DoctorCode) {
        this.VisitDate=VisitDate;
        this.VisitSymptoms=VisitSymptoms;
        this.TestResults=TestResults;
        this.VisitNumber=VisitNumber;
        this.Patient_PatientCode=Patient_PatientCode;
        this.Drug_DrugCode=Drug_DrugCode;
        this.Diagnosis_DiagnosisCode=Diagnosis_DiagnosisCode;
        this.Doctor_DoctorCode=Doctor_DoctorCode;
    }

}
