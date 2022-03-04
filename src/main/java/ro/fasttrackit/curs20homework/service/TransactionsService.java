package ro.fasttrackit.curs20homework.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs20homework.entity.Transaction;
import ro.fasttrackit.curs20homework.entity.TransactionType;
import ro.fasttrackit.curs20homework.repository.TransactionRepository;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class TransactionsService {
    private final TransactionRepository repository;

    public TransactionsService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAll(TransactionType type, double minAmount, double maxAmount) {
        if (type != null & minAmount != 0 & maxAmount != 0) {
            return repository.findByTypeAndAmountBetween(type, minAmount, maxAmount);
        } else if (minAmount != 0 & maxAmount != 0) {
            return repository.findByAmountBetween(minAmount, maxAmount);
        } else if (type != null & maxAmount != 0) {
            return repository.findByTypeAndAmountLessThan(type, maxAmount);
        } else if (type != null & minAmount != 0) {
            return repository.findByTypeAndAmountGreaterThan(type, minAmount);
        } else if (maxAmount != 0) {
            return repository.findByAmountLessThan(maxAmount);
        } else if (minAmount != 0) {
            return repository.findByAmountGreaterThan(minAmount);
        } else if (type != null) {
            return repository.findByType(type);
        } else {
            return repository.findAll();
        }
    }

    public Transaction getById(int id) {
        return repository.findById(id)
                .orElse(null);
    }

    public Transaction addOne(Transaction transaction) {
        return repository.save(transaction);
    }

    public Transaction replaceById(int id, Transaction transaction) {
        Transaction transactionToUpdate = repository.findById(id)
                .orElse(null);
        if (transactionToUpdate != null) {
            transactionToUpdate.setProduct(transaction.getProduct());
            transactionToUpdate.setType(transaction.getType());
            transactionToUpdate.setAmount(transaction.getAmount());
        }
        return repository.save(transactionToUpdate);
    }

    public Transaction deleteById(int id) {
        Transaction transactionToDelete = repository.findById(id)
                .orElse(null);
        if (transactionToDelete != null){
            repository.delete(transactionToDelete);
        }
        return transactionToDelete;
    }

    public Map<TransactionType, List<Transaction>> getMapByTransactionType(){
      return repository.findAll().stream()
              .collect(Collectors.groupingBy(Transaction::getType));
    }

    public Map<String, List<Transaction>> getMapByTransactionProduct() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
    }
}
