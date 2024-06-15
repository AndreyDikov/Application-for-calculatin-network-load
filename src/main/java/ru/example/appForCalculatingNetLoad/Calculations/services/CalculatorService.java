package ru.example.appForCalculatingNetLoad.Calculations.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.Calculations.entities.CalculationEntity;
import ru.example.appForCalculatingNetLoad.Calculations.repositories.CalculationRepository;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;
import ru.example.appForCalculatingNetLoad.security.securityUsers.repositories.SecurityUserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CalculatorService {

    private final CalculationRepository calculationRepository;

    public CalculationEntity getCurrentCalculation(SecurityUser securityUser) {
        List<CalculationEntity> calculations = calculationRepository.findAllByUser(securityUser);
        for (CalculationEntity calculation : calculations) {
            if (calculation.getIsCurrent()) {
                return calculation;
            }
        }
        return null;
    }
}
