package de.salil.homework.customer.service;

import de.salil.homework.customer.entity.UserEntity;
import de.salil.homework.customer.exception.UserAlreadyFoundException;
import de.salil.homework.customer.model.UserDTO;
import de.salil.homework.customer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        if (userRepository.findById(userDTO.getEmail()).isPresent()) {
            throw new UserAlreadyFoundException();
        }
        userDTO.setId(UUID.randomUUID().toString());
        userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        userDTO.setRole("ROLE_USER");
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO, user);
        userRepository.save(user);
        log.info("Saved user with id: {}", user.getId());
        userDTO.setPassword("*****");
        return userDTO;
    }
}
