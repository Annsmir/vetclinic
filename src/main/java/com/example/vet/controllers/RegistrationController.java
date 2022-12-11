package com.example.vet.controllers;

import com.example.vet.registration.RegistrationRequest;
import com.example.vet.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping("/auth/signin")
    public String register(@RequestPart String username, @RequestPart String password) {
        RegistrationRequest registrationRequest = new RegistrationRequest(username, password);
        return registrationService.register(registrationRequest);
    }
}
