package de.salil.homework.customer.controller;

import de.salil.homework.customer.model.CustomerDTO;
import de.salil.homework.customer.model.PersonDTO;
import de.salil.homework.customer.model.UserDTO;
import de.salil.homework.customer.service.PersonService;
import de.salil.homework.customer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")

public class CustomerController {

    @Autowired
    private final PersonService personService;

    @Autowired
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        UserDTO userDTO = userService.create(customerDTO.getUserDTO());
        return personService.create(customerDTO.getPersonDTO(), userDTO.getId());
    }
}
