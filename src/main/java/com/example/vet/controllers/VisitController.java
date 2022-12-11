package com.example.vet.controllers;

import com.example.vet.entity.Patient;
import com.example.vet.entity.Visit;
import com.example.vet.repo.PatientRepo;
import com.example.vet.repo.VisitRepository;
import com.example.vet.services.HeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class VisitController {

    private final VisitRepository visitRepository;
    private final PatientRepo patientRepo;
    private final HeaderService headerService;

    @GetMapping("/visit")
    public String blogVisit(Model model) {
        Iterable<Visit> visits = visitRepository.findAll();
        Iterable<Patient> patients = patientRepo.findAll();
        model.addAttribute("visits", visits);
        model.addAttribute("patients", patients);
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("userShow", headerService.usernameShow());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "blog-visit";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/add")
    public String blogAdd(Model model) {
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("userShow", headerService.usernameShow());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "blog-add";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/add")
    public String blogVisitAdd(@RequestParam String VisitDate,
                              @RequestParam String VisitSymptoms,
                              @RequestParam String TestResults,
                              @RequestParam Integer VisitNumber,
                              @RequestParam Integer Patient_PatientCode,
                               @RequestParam Integer Drug_DrugCode,
                               @RequestParam Integer Diagnosis_DiagnosisCode,
                               @RequestParam Integer Doctor_DoctorCode,
                              Model model) {
        Visit visit = new Visit(VisitDate, VisitSymptoms, TestResults, VisitNumber, Patient_PatientCode, Drug_DrugCode, Diagnosis_DiagnosisCode, Doctor_DoctorCode);
        visitRepository.save(visit);
        return "redirect:/visit";

    }

    @GetMapping("/visit/{id}")
    public String blogDetails(@PathVariable(value = "id") int VisitCode, Model model) {

        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("userShow", headerService.usernameShow());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        if (!visitRepository.existsById(VisitCode)) {
            return "redirect:/visit";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("admin")) {
            model.addAttribute("dellForm", "asd");
        }
        else {
            model.addAttribute("dellForm", "unauth");
        }

        Optional<Visit> visit = visitRepository.findById(VisitCode);
        ArrayList<Visit> res = new ArrayList<>();
        visit.ifPresent(res::add);
        model.addAttribute("visit", res); // post??


        return "blog-details";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/visit/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") int VisitCode, Model model) {

        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("userShow", headerService.usernameShow());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        if (!visitRepository.existsById(VisitCode)) {
            return "redirect:/visit";
        }

        Optional<Visit> visit = visitRepository.findById(VisitCode);
        ArrayList<Visit> res = new ArrayList<>();
        visit.ifPresent(res::add);
        model.addAttribute("visit", res);

        return "blog-edit";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/visit/{id}/edit")
    public String blogVisitUpdate(@PathVariable(value = "id") int VisitCode,
                                  @RequestParam String VisitDate,
                                  @RequestParam String VisitSymptoms,
                                  @RequestParam String TestResults,
                                  @RequestParam Integer VisitNumber,
                                  @RequestParam Integer Patient_PatientCode,
                                  @RequestParam Integer Drug_DrugCode,
                                  @RequestParam Integer Diagnosis_DiagnosisCode,
                                  @RequestParam Integer Doctor_DoctorCode,
                                 Model model) {
        Visit visit = visitRepository.findById(VisitCode).orElseThrow();
        visit.setVisitDate(VisitDate);
        visit.setVisitSymptoms(VisitSymptoms);
        visit.setTestResults(TestResults);
        visit.setVisitNumber(VisitNumber);
        visit.setPatient_PatientCode(Patient_PatientCode);
        visit.setDrug_DrugCode(Drug_DrugCode);
        visit.setDiagnosis_DiagnosisCode(Diagnosis_DiagnosisCode);
        visit.setDoctor_DoctorCode(Doctor_DoctorCode);
        visitRepository.save(visit);
        return "redirect:/visit";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/visit/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") int VisitCode, Model model) {
        Visit visit = visitRepository.findById(VisitCode).orElseThrow();
        visitRepository.delete(visit);
        return "redirect:/visit";
    }
}
