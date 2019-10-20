package de.salil.homework.loan.controller;

import de.salil.homework.loan.model.LoanDTO;
import de.salil.homework.loan.model.LoanSearchDTO;
import de.salil.homework.loan.model.LoanSearchResponseDTO;
import de.salil.homework.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loanapplications")

public class LoanController {

    @Autowired
    private final LoanService loanService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanDTO createLoan(@Valid @RequestBody LoanDTO loanDTO) {
        return loanService.create(loanDTO);
    }

    @GetMapping
    public List<LoanDTO> getLoansByUserId(@RequestParam(value = "userId", required = true) long userId) {
        return loanService.getLoansByUserId(userId);
    }

    @PostMapping("/search")
    public List<LoanSearchResponseDTO> findUsersByLoanCriteria(@Valid @RequestBody LoanSearchDTO loanSearchDTO) {
        return loanService.findUsersByLoanCriteria(loanSearchDTO);
    }
}
