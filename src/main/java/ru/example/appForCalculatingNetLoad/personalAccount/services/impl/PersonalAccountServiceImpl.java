package ru.example.appForCalculatingNetLoad.personalAccount.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.personalAccount.repositories.PersonalAccountRepository;
import ru.example.appForCalculatingNetLoad.personalAccount.services.PersonalAccountService;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalAccountServiceImpl implements PersonalAccountService {

    private final PersonalAccountRepository personalAccountRepository;

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
}
