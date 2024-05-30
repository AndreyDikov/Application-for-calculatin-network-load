package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.security.securityUsers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.security.securityUsers.entities.SecurityUser;

import java.util.Optional;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {

    Optional<SecurityUser> findByEmail(String email);
}
