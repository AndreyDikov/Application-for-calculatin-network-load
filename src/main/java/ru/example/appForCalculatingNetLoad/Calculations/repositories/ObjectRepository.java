package ru.example.appForCalculatingNetLoad.Calculations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.appForCalculatingNetLoad.Calculations.entities.ObjectEntity;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.List;

public interface ObjectRepository extends JpaRepository<ObjectEntity, Long> {

    List<ObjectEntity> findAllByUser(SecurityUser securityUser);
}
