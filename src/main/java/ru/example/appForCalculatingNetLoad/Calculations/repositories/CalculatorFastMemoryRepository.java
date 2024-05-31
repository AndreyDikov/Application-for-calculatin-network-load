package ru.example.appForCalculatingNetLoad.Calculations.repositories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import ru.example.appForCalculatingNetLoad.Calculations.dto.ObjectDto;

import java.util.List;

@Getter
@Setter
@Repository
@AllArgsConstructor
public class CalculatorFastMemoryRepository {

    private List<ObjectDto> objectsDto;

    public void addObjectDto(ObjectDto objectDto) {
        objectsDto.add(objectDto);
    }

    public List<ObjectDto> getAllObjects() {
        return objectsDto;
    }
}
