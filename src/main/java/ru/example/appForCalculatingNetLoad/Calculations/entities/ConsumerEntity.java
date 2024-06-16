package ru.example.appForCalculatingNetLoad.Calculations.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.Section.Consumers;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "consumers")
public class ConsumerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Integer number;

    @Enumerated(EnumType.STRING)
    Consumers type;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "section_id")
    SectionEntity section;
}
