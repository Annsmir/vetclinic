package com.example.vet.services;

import com.example.vet.appuser.AppUser;
import com.example.vet.appuser.AppUserRole;
import com.example.vet.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;


    public String register(RegistrationRequest request) {
        return appUserService.signUpUser(new AppUser(request.getUsername(), request.getPassword(), AppUserRole.USER));
    }
}
