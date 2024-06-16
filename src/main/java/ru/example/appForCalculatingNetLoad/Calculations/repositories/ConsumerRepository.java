package ru.example.appForCalculatingNetLoad.Calculations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.appForCalculatingNetLoad.Calculations.entities.ConsumerEntity;

public interface ConsumerRepository extends JpaRepository<ConsumerEntity, Long> {
}
