package ru.example.appForCalculatingNetLoad.personalAccount.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.PasswordsDontMatchException;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.UserNotFoundException;
import ru.example.appForCalculatingNetLoad.personalAccount.exceptions.WrongOldPasswordException;
import ru.example.appForCalculatingNetLoad.personalAccount.repositories.PersonalAccountRepository;
import ru.example.appForCalculatingNetLoad.personalAccount.services.PersonalAccountService;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalAccountServiceImpl implements PersonalAccountService {

    private final PersonalAccountRepository personalAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails updateUser(Long userId, UserDetails userDetails) {
        Optional<SecurityUser> securityUserOptional
                = personalAccountRepository.findById(userId);

        if (securityUserOptional.isPresent()) {
            SecurityUser securityUserEntity = securityUserOptional.get();
            SecurityUser user = (SecurityUser) userDetails;
            securityUserEntity.setName(user.getName());
            securityUserEntity.setSurname(user.getSurname());
            securityUserEntity.setPatronymic(user.getPatronymic());
            securityUserEntity.setEmail(user.getEmail());
            securityUserEntity.setPhone(user.getPhone());
            personalAccountRepository.save(securityUserEntity);
            return securityUserEntity;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public UserDetails updateUserPassword(SecurityUser currentUser,
                                          String oldPassword,
                                          String newPassword,
                                          String newPasswordRepeat)
            throws PasswordsDontMatchException,
            WrongOldPasswordException,
            UserNotFoundException {
        Optional<SecurityUser> securityUserOptional
                = personalAccountRepository.findById(currentUser.getId());

        if (securityUserOptional.isPresent()) {
            SecurityUser securityUserEntity = securityUserOptional.get();
            if (passwordEncoder.matches(oldPassword, securityUserEntity.getPassword())) {
                if (newPassword.equals(newPasswordRepeat)) {
                    securityUserEntity.setPassword(passwordEncoder.encode(newPassword));
                    personalAccountRepository.save(securityUserEntity);
                    return securityUserEntity;
                } else {
                    throw new PasswordsDontMatchException("Новые пароли не совпадают!");
                }
            } else {
                throw new WrongOldPasswordException("Неверный старый пароль!");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
