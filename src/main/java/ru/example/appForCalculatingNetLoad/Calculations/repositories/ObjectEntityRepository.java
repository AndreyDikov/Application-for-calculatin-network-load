package ru.example.appForCalculatingNetLoad.Calculations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.appForCalculatingNetLoad.Calculations.entities.ObjectEntity;

public interface ObjectEntityRepository extends JpaRepository<ObjectEntity, Long> {
}
