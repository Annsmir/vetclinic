package com.example.vet.controllers;

import com.example.vet.entity.*;
import com.example.vet.repo.*;
import com.example.vet.services.HeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final HeaderService headerService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("userShow", headerService.usernameShow());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "home";
    }

    private final DoctorRepository doctorRepository;
    @GetMapping("/doctors")
    public String doctorList(Model model) {
        Iterable<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        model.addAttribute("title", "Наши врачи");
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("userShow", headerService.usernameShow());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "doctorList";
    }

    private final DiagnosisRepository diagnosisRepository;
    private final DrugRepository drugRepository;
    @Autowired
    private final AnimalRepository animalRepository;
    @GetMapping("/adminPage")
    public String adminPage(Model model) {
        Iterable<Diagnosis> diagnoses = diagnosisRepository.findAll();
        Iterable<Drug> drugs = drugRepository.findAll();
        Iterable<Animal> animals = animalRepository.findAll();
        Iterable<Owner> owners = ownerRepository.findAll();
        model.addAttribute("diagnoses", diagnoses);
        model.addAttribute("drugs", drugs);
        model.addAttribute("animals", animals);
        model.addAttribute("owners", owners);
        model.addAttribute("title", "Админ. страница");
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("userShow", headerService.usernameShow());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "adminPage";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас");
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("userShow", headerService.usernameShow());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "about";
    }

    @GetMapping("/auth/login")
    public String getLoginPage(Model model) {
        model.addAttribute("title", "Логин");
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "login";
    }

    @GetMapping("/auth/signin")
    public String getSigninPage(Model model) {
        model.addAttribute("title", "Регистрация");
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "signin";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/diagnosisAdd")
    public String getDiagnosisAdd(Model model) {
        model.addAttribute("title", "Добавить диагноз");
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "diagnosisAdd";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/diagnosisAdd")
    public String diagnosisAdd(@RequestParam String DiagnosisName,
                          @RequestParam String DrugRecommendation,
                          @RequestParam String TreatmentRecommendation,
                          Model model) {
        Diagnosis diagnosis = new Diagnosis(DiagnosisName, DrugRecommendation, TreatmentRecommendation);
        diagnosisRepository.save(diagnosis);
        return "redirect:/adminPage";

    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/diagnosis/{id}/remove")
    public String diagnosisDelete(@PathVariable(value = "id") int DiagnosisCode, Model model) {
        Diagnosis diagnosis = diagnosisRepository.findById(DiagnosisCode).orElseThrow();
        diagnosisRepository.delete(diagnosis);
        return "redirect:/adminPage";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/drugAdd")
    public String getDrugAdd(Model model) {
        model.addAttribute("title", "Добавить препарат");
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "drugAdd";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/drugAdd")
    public String drugAdd(@RequestParam String DrugName,
                          @RequestParam String DrugDosage,
                          @RequestParam String DrugManufacturer,
                          @RequestParam String DrugContraindications,
                          @RequestParam String DrugUseTime,
                          @RequestParam String DrugSideEffects,
                          Model model) {
        Drug drug = new Drug(DrugName, DrugDosage, DrugManufacturer, DrugContraindications, DrugUseTime, DrugSideEffects);
        drugRepository.save(drug);
        return "redirect:/adminPage";

    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/drug/{id}/remove")
    public String drugDelete(@PathVariable(value = "id") int DrugCode, Model model) {
        Drug drug = drugRepository.findById(DrugCode).orElseThrow();
        drugRepository.delete(drug);
        return "redirect:/adminPage";
    }

    private final PatientRepo patientRepo;
    @GetMapping("/showAllPets")
    public String showAllPets(Model model) {
        Iterable<Patient> patients = patientRepo.findAll();
        model.addAttribute("patients", patients);
        model.addAttribute("title", "Все пациенты");
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("userShow", headerService.usernameShow());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "showAllPets";
    }

    @Autowired
    private final OwnerRepository ownerRepository;
    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/patient/{id}/edit")
    public String patientEdit(@PathVariable(value = "id") int PatientCode, Model model) {
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("userShow", headerService.usernameShow());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());

        Patient patient = patientRepo.findById(PatientCode).get();
        model.addAttribute("patient", patient);

        List<Animal> listAnimals = animalRepository.findAll();
        model.addAttribute("listAnimals", listAnimals);
        List<Owner> listOwners = ownerRepository.findAll();
        model.addAttribute("listOwners", listOwners);

        return "patientAdd";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/patient/{id}/remove")
    public String patientDelete(@PathVariable(value = "id") int PatientCode, Model model) {
        patientRepo.deleteById(PatientCode);

        return "redirect:/showAllPets";
    }

    @GetMapping("/showMyPets")
    public String showMyPets(Model model) {
        Iterable<Patient> patients = patientRepo.findAll();
        model.addAttribute("patients", patients);
        model.addAttribute("title", "Мои животные");
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("userShow", headerService.usernameShow());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "showMyPets";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/patientAdd")
    public String getPatientAdd(Model model) {
        model.addAttribute("title", "Добавить пациента");
        model.addAttribute("patient", new Patient());
        List<Animal> listAnimals = animalRepository.findAll();
        model.addAttribute("listAnimals", listAnimals);
        List<Owner> listOwners = ownerRepository.findAll();
        model.addAttribute("listOwners", listOwners);
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "patientAdd";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/patientAdd")
    public String patientAdd(Patient patient) {
        patientRepo.save(patient);
        return "redirect:/showAllPets";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/ownerAdd")
    public String getOwnerAdd(Model model) {
        model.addAttribute("title", "Добавить хозяина");
        model.addAttribute("addNews", headerService.isUser());
        model.addAttribute("adminPage", headerService.adminPageShow());
        model.addAttribute("allVisits", headerService.isUserr());
        model.addAttribute("showAllPets", headerService.showAll());
        model.addAttribute("showMyPets", headerService.showMy());
        return "ownerAdd";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/ownerAdd")
    public String ownerAdd(@RequestParam String OwnerName,
                          @RequestParam String OwnerTelephone,
                          Model model) {
        Owner owner = new Owner(OwnerName, OwnerTelephone);
        ownerRepository.save(owner);
        return "redirect:/adminPage";

    }
}