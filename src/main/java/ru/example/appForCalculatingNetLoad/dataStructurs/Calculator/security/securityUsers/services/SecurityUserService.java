package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.security.securityUsers.services;


import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.security.securityUsers.entities.SecurityUser;

public interface SecurityUserService {

    SecurityUser getUserByEmail(String email);

    SecurityUser saveUser(SecurityUser securityUser);
}
