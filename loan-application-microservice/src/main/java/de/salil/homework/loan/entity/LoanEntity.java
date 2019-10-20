package de.salil.homework.loan.entity;

import de.salil.homework.loan.model.LoanStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "loan")
public class LoanEntity {
    @Id
    @SequenceGenerator(name = "loan_seq", sequenceName = "loan_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "loan_seq")
    private long id;
    private Double amount;
    private Integer duration;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
