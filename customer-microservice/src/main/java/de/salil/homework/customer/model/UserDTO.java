package de.salil.homework.customer.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserDTO {
    private String id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String role;
}
