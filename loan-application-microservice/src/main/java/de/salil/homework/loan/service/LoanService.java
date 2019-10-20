package de.salil.homework.loan.service;

import de.salil.homework.loan.entity.LoanEntity;
import de.salil.homework.loan.entity.UserEntity;
import de.salil.homework.loan.exception.UserNotFoundException;
import de.salil.homework.loan.model.LoanDTO;
import de.salil.homework.loan.model.LoanSearchDTO;
import de.salil.homework.loan.model.LoanSearchResponseDTO;
import de.salil.homework.loan.model.LoanStatus;
import de.salil.homework.loan.repository.LoanRepository;
import de.salil.homework.loan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    @Transactional
    public LoanDTO create(LoanDTO loanDTO) {
        UserEntity user = userRepository.findById(loanDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException());
        loanDTO.setStatus(LoanStatus.CREATED);
        LoanEntity loan = convertLoanDTOToEntity(loanDTO);
        loan.setUser(user);
        loanRepository.save(loan);
        log.info("Saved loan with id: {}", loan.getId());
        loanDTO.setUserId(user.getId());
        loanDTO.setId(loan.getId());
        return loanDTO;
    }

    private LoanEntity convertLoanDTOToEntity(LoanDTO loanDTO) {
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setAmount(loanDTO.getAmount());
        loanEntity.setDuration(loanDTO.getDuration());
        loanEntity.setStatus(loanDTO.getStatus());
        return loanEntity;
    }

    public List<LoanDTO> getLoansByUserId(final long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        List<LoanEntity> loansByIdList = loanRepository.findLoansByUserId(user.getId());
        if(isEmpty(loansByIdList)) {
            return Collections.emptyList();
        }
        return loansByIdList.stream().map(
                ln -> LoanDTO.builder()
                        .userId(user.getId())
                        .id(ln.getId())
                        .amount(ln.getAmount())
                        .duration(ln.getDuration())
                        .status(ln.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public List<LoanSearchResponseDTO> findUsersByLoanCriteria(LoanSearchDTO loanSearchDTO) {
        List<LoanEntity> loansByIdList = loanRepository
                .findUsersByLoanCriteria(loanSearchDTO.getMinAmount(),
                        loanSearchDTO.getMaxAmount(), loanSearchDTO.getStatus());
        if(isEmpty(loansByIdList)) {
            return Collections.emptyList();
        }
        return loansByIdList.stream().map(
                ln -> LoanSearchResponseDTO.builder()
                        .userId(ln.getUser().getId())
                        .email(ln.getUser().getEmail())
                        .build())
                .collect(Collectors.toList());
    }
}
