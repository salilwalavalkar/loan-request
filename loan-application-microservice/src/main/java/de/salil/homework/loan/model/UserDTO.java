package de.salil.homework.loan.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserDTO {
    private long id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String role;
}
