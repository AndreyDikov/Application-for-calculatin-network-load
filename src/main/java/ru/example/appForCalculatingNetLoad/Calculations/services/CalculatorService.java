package ru.example.appForCalculatingNetLoad.Calculations.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.Calculations.entities.CalculationEntity;
import ru.example.appForCalculatingNetLoad.Calculations.repositories.CalculationRepository;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CalculatorService {

    private final CalculationRepository calculationRepository;

    public CalculationEntity getCalculationById(Long id) {
        return calculationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Нет расчёта с id " + id));
    }

    public CalculationEntity getCurrentCalculation(SecurityUser securityUser) {
        List<CalculationEntity> calculations = calculationRepository.findAllByUser(securityUser);
        for (CalculationEntity calculation : calculations) {
            if (calculation.getIsCurrent()) {
                return calculation;
            }
        }
        return null;
    }

    public CalculationEntity getCurrentUserCalculation(SecurityUser securityUser) {
        Optional<CalculationEntity> currentUserCalculation
                = calculationRepository.findByUserAndIsCurrent(securityUser, true);

        return currentUserCalculation.orElseThrow(() -> new RuntimeException("No current calculation"));
    }

    public CalculationEntity saveCalculation(CalculationEntity calculation) {
        return calculationRepository.save(calculation);
    }

    public CalculationEntity performCalculation(SecurityUser securityUser,
                                                CalculationEntity calculation) {
        CalculationEntity calculationEntity
                = getCalculationById(calculation.getId());
        BeanUtils.copyProperties(calculation, calculationEntity,
                "id", "isCurrent", "user", "sections");
        calculationEntity.setIsCurrent(false);

        saveCalculation(calculationEntity);

        return createCalculation(securityUser);
    }

    public CalculationEntity createCalculation(SecurityUser securityUser) {
        CalculationEntity calculation = new CalculationEntity();
        calculation.setUser(securityUser);
        calculation.setIsCurrent(true);

        return saveCalculation(calculation);
    }
}
