package de.salil.homework.loan.repository;

import de.salil.homework.loan.entity.LoanEntity;
import de.salil.homework.loan.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<LoanEntity, String> {

    @Query("SELECT ln FROM LoanEntity AS ln WHERE ln.user.id = ?1 order by ln.id desc")
    List<LoanEntity> findLoansByUserId(final long userId);

    @Query("SELECT ln FROM LoanEntity AS ln WHERE (ln.amount >= ?1 and ln.amount < ?2) and ln.status = ?3 order by ln.user.id")
    List<LoanEntity> findUsersByLoanCriteria(final Double minAmount, final Double maxAmount, final LoanStatus status);
}
