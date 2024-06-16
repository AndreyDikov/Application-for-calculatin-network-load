package ru.example.appForCalculatingNetLoad.Calculations.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.Calculations.entities.CalculationEntity;
import ru.example.appForCalculatingNetLoad.Calculations.entities.SectionEntity;
import ru.example.appForCalculatingNetLoad.Calculations.repositories.SectionRepository;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;

    public SectionEntity getById(Long id) {
        Optional<SectionEntity> sectionOptional = sectionRepository.findById(id);

        return sectionOptional.orElseThrow(() -> new IllegalArgumentException("Секция с id " + id + " не найдена"));
    }

    public List<SectionEntity> getByCurrentUserCalculation(SecurityUser user) {
        List<SectionEntity> currentUserSections = sectionRepository
                .findByCalculation_IsCurrentAndCalculation_User(true, user);

        return currentUserSections;
    }

    public List<SectionEntity> getSectionsByUserAndCalculation(SecurityUser user,
                                                               CalculationEntity calculation) {
        return sectionRepository.findByCalculationAndCalculation_User(calculation, user);
    }

    Long getNumberSections() {
        return sectionRepository.getNumberSections();
    }

    public SectionEntity saveSection(SectionEntity section) {
        return sectionRepository.save(section);
    }

    public void deleteSection(SectionEntity section) {
        sectionRepository.delete(section);
    }
}
