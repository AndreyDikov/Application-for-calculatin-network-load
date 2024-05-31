package ru.example.appForCalculatingNetLoad.personalAccount.services;

import org.springframework.security.core.userdetails.UserDetails;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.PasswordsDontMatchException;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.UserNotFoundException;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.WrongOldPasswordException;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

public interface PersonalAccountService {

    UserDetails updateUser(Long userId, UserDetails user);

    UserDetails updateUserPassword(SecurityUser currentUser,
                                   String oldPassword,
                                   String newPassword,
                                   String newPasswordRepeat)
            throws PasswordsDontMatchException,
            WrongOldPasswordException,
            UserNotFoundException;
}
