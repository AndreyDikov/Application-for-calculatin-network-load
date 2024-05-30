package ru.example.appForCalculatingNetLoad.security.securityUsers.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;
import ru.example.appForCalculatingNetLoad.security.securityUsers.repositories.SecurityUserRepository;
import ru.example.appForCalculatingNetLoad.security.securityUsers.services.SecurityUserService;

@Service
@RequiredArgsConstructor
public class SecurityUserServiceImpl implements SecurityUserService {

    private final SecurityUserRepository securityUserRepository;

    @Override
    public SecurityUser getUserByEmail(String email) {
        return securityUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public SecurityUser saveUser(SecurityUser securityUser) {
        return securityUserRepository.save(securityUser);
    }
}

