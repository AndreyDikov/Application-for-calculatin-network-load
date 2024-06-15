package ru.example.appForCalculatingNetLoad.Calculations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.appForCalculatingNetLoad.Calculations.entities.CalculationEntity;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalculationRepository extends JpaRepository<CalculationEntity, Long> {

    List<CalculationEntity> findAllByUser(SecurityUser securityUser);

    Optional<CalculationEntity> findByUserAndIsCurrent(SecurityUser securityUser, Boolean isCurrent);
}
