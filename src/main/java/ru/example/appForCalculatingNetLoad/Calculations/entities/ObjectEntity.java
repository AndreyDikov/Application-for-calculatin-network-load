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
@Table(name = "objects")
public class ObjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String region;
    String address;
    String square;

    @ManyToOne
    @JoinColumn(name = "user_id")
    SecurityUser user;

    @OneToMany(mappedBy = "object")
    List<CalculationEntity> calculations;
}
