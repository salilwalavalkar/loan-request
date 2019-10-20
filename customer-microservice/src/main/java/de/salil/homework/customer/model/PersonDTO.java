package de.salil.homework.customer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String postalCode;
}
