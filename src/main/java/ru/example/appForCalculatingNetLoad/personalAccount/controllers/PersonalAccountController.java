package ru.example.appForCalculatingNetLoad.personalAccount.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.example.appForCalculatingNetLoad.personalAccount.services.PersonalAccountService;
import ru.example.appForCalculatingNetLoad.security.auth.controllers.AuthenticationController;
import ru.example.appForCalculatingNetLoad.security.auth.dto.AuthenticationRequest;
import ru.example.appForCalculatingNetLoad.security.auth.dto.AuthenticationResponse;
import ru.example.appForCalculatingNetLoad.security.auth.services.AuthenticationService;
import ru.example.appForCalculatingNetLoad.security.auth.services.JwtService;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;
import ru.example.appForCalculatingNetLoad.security.securityUsers.services.SecurityUserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/personal-account")
public class PersonalAccountController {

    private final PersonalAccountService personalAccountService;
    private final SecurityUserService securityUserService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @ModelAttribute("currentUser")
    public SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @GetMapping
    public String getPersonalAccountPage() {
        return "personal-account";
    }

    @PostMapping("/{userId}/update-user")
    public String updateCurrentUser(@PathVariable Long userId,
                                    SecurityUser currentUser,
                                    HttpServletResponse httpServletResponse) {
        UserDetails userDetails = personalAccountService.updateUser(userId, currentUser);
        authenticationService.updateCookie(
                AuthenticationResponse
                        .builder()
                        .token(jwtService.generateToken(userDetails))
                        .build(),
                httpServletResponse);
        return "redirect:/personal-account";
    }
}
