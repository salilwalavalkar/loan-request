package de.salil.homework.customer.service;

import de.salil.homework.customer.entity.PersonEntity;
import de.salil.homework.customer.entity.UserEntity;
import de.salil.homework.customer.exception.UserNotFoundException;
import de.salil.homework.customer.model.PersonDTO;
import de.salil.homework.customer.repository.PersonRepository;
import de.salil.homework.customer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    @Transactional
    public PersonDTO create(PersonDTO personDTO, String userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        PersonEntity person = new PersonEntity();
        BeanUtils.copyProperties(personDTO, person);
        person.setUser(user);
        personRepository.save(person);
        log.info("Saved person with id: {}", person.getId());
        personDTO.setId(person.getId());
        return personDTO;
    }
}
