package ru.example.appForCalculatingNetLoad.security.securityUsers.services;


import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

public interface SecurityUserService {

    SecurityUser getUserByEmail(String email);

    SecurityUser saveUser(SecurityUser securityUser);
}
