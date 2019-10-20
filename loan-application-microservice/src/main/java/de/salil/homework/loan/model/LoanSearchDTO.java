package de.salil.homework.loan.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoanSearchDTO {

    @NotNull
    private Double minAmount;

    @NotNull
    private Double maxAmount;

    private LoanStatus status;
}
