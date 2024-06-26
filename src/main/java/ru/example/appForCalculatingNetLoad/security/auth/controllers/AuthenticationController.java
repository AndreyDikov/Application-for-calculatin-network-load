package ru.example.appForCalculatingNetLoad.security.auth.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.appForCalculatingNetLoad.security.auth.dto.AuthenticationRequest;
import ru.example.appForCalculatingNetLoad.security.auth.dto.AuthenticationResponse;
import ru.example.appForCalculatingNetLoad.security.auth.dto.RegisterRequest;
import ru.example.appForCalculatingNetLoad.security.auth.services.AuthenticationService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/signup")
    public String registration(Model model) {
        model.addAttribute("registrationForm", new RegisterRequest());
        return "security/registration";
    }

    @PostMapping("/register")
    public String registration(RegisterRequest request, Model model) {
        authenticationService.register(request);
        model.addAttribute("authenticationRequest", new AuthenticationRequest());
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("authenticationRequest", new AuthenticationRequest());
        return "security/authorization";
    }

    @PostMapping("/authenticate")
    public String authenticate(
            AuthenticationRequest request,
            HttpServletResponse httpServletResponse) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        authenticationService.updateCookie(authenticationResponse, httpServletResponse);
        return "redirect:/personal-account";
    }
}
