package ru.example.appForCalculatingNetLoad.Calculations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.appForCalculatingNetLoad.Calculations.entities.SectionEntity;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.List;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    List<SectionEntity> findByCalculation_IsCurrentAndCalculation_User(Boolean isCurrent, SecurityUser user);
}
