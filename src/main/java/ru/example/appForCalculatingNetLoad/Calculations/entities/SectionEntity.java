package ru.example.appForCalculatingNetLoad.Calculations.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "sections")
public class SectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Double limitValue;

    @ManyToOne
    @JoinColumn(name = "calculation_id")
    CalculationEntity calculation;

    @OneToMany(mappedBy = "section")
    List<ConsumerEntity> consumers;
}
