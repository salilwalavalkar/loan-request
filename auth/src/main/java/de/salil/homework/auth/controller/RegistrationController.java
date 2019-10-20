package de.salil.homework.auth.controller;

import de.salil.homework.auth.model.UserDTO;
import de.salil.homework.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.create(userDTO));
    }
}
