package de.salil.homework.loan.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class LoanDTO {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Double amount;

    @NotNull
    @Min(1)
    @Max(12)
    private Integer duration;

    private LoanStatus status;
}
