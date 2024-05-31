package ru.example.appForCalculatingNetLoad.personalAccount.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface PersonalAccountService {

    UserDetails updateUser(Long userId, UserDetails user);
}
