package ru.example.appForCalculatingNetLoad.personalAccount.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

public interface PersonalAccountRepository extends JpaRepository<SecurityUser, Long> {
}
