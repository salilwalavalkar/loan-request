package de.salil.homework.customer.repository;

import de.salil.homework.customer.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, String> {
}
