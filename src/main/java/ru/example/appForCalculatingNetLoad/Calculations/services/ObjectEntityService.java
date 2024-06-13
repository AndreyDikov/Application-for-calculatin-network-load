package ru.example.appForCalculatingNetLoad.Calculations.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.Calculations.entities.ObjectEntity;
import ru.example.appForCalculatingNetLoad.Calculations.repositories.ObjectEntityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectEntityService {

    private final ObjectEntityRepository objectEntityRepository;

    public List<ObjectEntity> getAll() {
        return objectEntityRepository.findAll();
    }

    public void saveObjectEntity(ObjectEntity objectEntity) {
        objectEntityRepository.save(objectEntity);
    }
}
