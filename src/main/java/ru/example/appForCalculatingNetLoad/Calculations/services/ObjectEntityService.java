package ru.example.appForCalculatingNetLoad.Calculations.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.Calculations.entities.ObjectEntity;
import ru.example.appForCalculatingNetLoad.Calculations.repositories.ObjectRepository;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectEntityService {

    private final ObjectRepository objectRepository;

    public List<ObjectEntity> getAll() {
        return objectRepository.findAll();
    }

    public List<ObjectEntity> getAllByUser(SecurityUser securityUser) {
        return objectRepository.findAllByUser(securityUser);
    }

    public void saveObjectEntity(ObjectEntity objectEntity) {
        objectRepository.save(objectEntity);
    }
}
