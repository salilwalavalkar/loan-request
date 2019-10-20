package de.salil.homework.loan.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class LoanSearchResponseDTO {
    @NotBlank
    private Long userId;
    @NotBlank
    private String email;
}
