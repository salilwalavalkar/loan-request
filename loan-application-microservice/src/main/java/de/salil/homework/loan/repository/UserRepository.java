package de.salil.homework.loan.repository;

import de.salil.homework.loan.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
