package ru.example.appForCalculatingNetLoad.Calculations.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.Calculations.entities.SectionEntity;
import ru.example.appForCalculatingNetLoad.Calculations.repositories.SectionEntityRepository;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionEntityRepository sectionEntityRepository;

    public List<SectionEntity> getByCurrentUserCalculation(SecurityUser user) {
        List<SectionEntity> currentUserSections = sectionEntityRepository
                .findByCalculation_IsCurrentAndCalculation_User(true, user);

        return currentUserSections;
    }
}
