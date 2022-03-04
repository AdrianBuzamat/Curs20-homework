package ro.fasttrackit.curs20homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs20homework.entity.Transaction;
import ro.fasttrackit.curs20homework.entity.TransactionType;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByAmountGreaterThan(double minAmount);
    List<Transaction> findByAmountLessThan(double maxAmount);
    List<Transaction> findByTypeAndAmountGreaterThan(TransactionType type, double minAmount);
    List<Transaction> findByTypeAndAmountLessThan(TransactionType type, double maxAmount);
    List<Transaction> findByAmountBetween(double minAmount, double maxAmount);
    List<Transaction> findByTypeAndAmountBetween(TransactionType type, double minAmount, double maxAmount);
}
