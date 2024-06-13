package ru.example.appForCalculatingNetLoad.Calculations.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "calculations")
public class CalculationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String customer;
    String note;
    Boolean isCurrent;

    @ManyToOne
    @JoinColumn(name = "object_id")
    ObjectEntity object;

    @ManyToOne
    @JoinColumn(name = "user_id")
    SecurityUser user;

    @OneToMany(mappedBy = "calculation")
    List<SectionEntity> sections;
}
