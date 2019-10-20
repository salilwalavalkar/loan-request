package de.salil.homework.loan.service;

import de.salil.homework.loan.entity.LoanEntity;
import de.salil.homework.loan.entity.UserEntity;
import de.salil.homework.loan.model.LoanDTO;
import de.salil.homework.loan.model.LoanStatus;
import de.salil.homework.loan.repository.LoanRepository;
import de.salil.homework.loan.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Sample test class with single test create method added to show use of mocks.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class LoanServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoanRepository loanRepository;

    @Test
    public void whenUserIsFoundLoanIsCreated() {

        long userId = 101;

        LoanDTO loanRequestDTO = LoanDTO.builder().userId(userId).amount(200.20).duration(6).build();

        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setEmail("abc@myemail.com");
        user.setPassword("pass@1word");
        user.setRole("USER_ROLE");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        LoanDTO loanExpectedResponseDTO = LoanDTO.builder()
                .userId(loanRequestDTO.getUserId())
                .amount(loanRequestDTO.getAmount())
                .duration(loanRequestDTO.getDuration())
                .status(LoanStatus.CREATED)
                .id(101L)
                .build();

        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setId(101L);
        loanEntity.setDuration(loanRequestDTO.getDuration());
        loanEntity.setStatus(loanRequestDTO.getStatus());
        loanEntity.setAmount(loanExpectedResponseDTO.getAmount());
        loanEntity.setUser(user);
        when(loanRepository.save(any(LoanEntity.class))).thenReturn(loanEntity);

        LoanService loanService = new LoanService(loanRepository, userRepository);
        LoanDTO loanResponseDTO = loanService.create(loanRequestDTO);
        assertEquals(loanExpectedResponseDTO.getUserId(), loanResponseDTO.getUserId());
        assertEquals(loanExpectedResponseDTO.getAmount(), loanResponseDTO.getAmount());
        assertEquals(loanExpectedResponseDTO.getDuration(), loanResponseDTO.getDuration());
        assertEquals(loanExpectedResponseDTO.getStatus(), LoanStatus.CREATED);
    }

}
