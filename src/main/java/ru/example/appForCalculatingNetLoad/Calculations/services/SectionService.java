package ru.example.appForCalculatingNetLoad.Calculations.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.Calculations.entities.SectionEntity;
import ru.example.appForCalculatingNetLoad.Calculations.repositories.SectionRepository;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;

    public List<SectionEntity> getByCurrentUserCalculation(SecurityUser user) {
        List<SectionEntity> currentUserSections = sectionRepository
                .findByCalculation_IsCurrentAndCalculation_User(true, user);

        return currentUserSections;
    }
}
