package de.salil.homework.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    @NotNull(message = "User id cannot be empty")
    private Long userId;

    @NotNull(message = "First name cannot be empty")
    private String firstName;

    // Add similar messages for others
    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String postalCode;

    /**
     * Adding these below methods in order to reuse the existing service classes.
     */

    @JsonIgnore
    public UserDTO getUserDTO() {
        return new UserDTO.UserDTOBuilder().build();
    }

    @JsonIgnore
    public PersonDTO getPersonDTO() {
        return new PersonDTO.PersonDTOBuilder().firstName(getFirstName())
                .lastName(getLastName()).phone(getPhone()).address(getAddress())
                .city(getCity()).postalCode(getPostalCode()).build();
    }
}
