package ru.example.appForCalculatingNetLoad.Calculations.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.Calculations.dto.ObjectDto;
import ru.example.appForCalculatingNetLoad.Calculations.repositories.CalculatorFastMemoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CalculatorService {

    private final CalculatorFastMemoryRepository fastMemoryRepository;

    public void setObjectSettings(ObjectDto objectDto) {
        fastMemoryRepository.addObjectDto(objectDto);
    }

    public List<ObjectDto> getAllObjects() {
        return fastMemoryRepository.getAllObjects();
    }
}
