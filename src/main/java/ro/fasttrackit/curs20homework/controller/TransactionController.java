package ro.fasttrackit.curs20homework.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs20homework.entity.Transaction;
import ro.fasttrackit.curs20homework.entity.TransactionType;
import ro.fasttrackit.curs20homework.service.TransactionsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"transactions"})
public class TransactionController {
    private final TransactionsService service;

    public TransactionController(TransactionsService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> getAll(
            @RequestParam(required = false) TransactionType type,
            @RequestParam(defaultValue = "0") double minAmount,
            @RequestParam(defaultValue = "0") double maxAmount) {
        return service.getAll(type, minAmount, maxAmount);
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return service.addOne(transaction);
    }

    @PutMapping("{id}")
    public Transaction replaceWithID(@PathVariable int id, @RequestBody Transaction transaction) {
        return service.replaceById(id, transaction);
    }

    @DeleteMapping("{id}")
    public Transaction deleteWithID(@PathVariable int id) {
        return service.deleteById(id);
    }

    @GetMapping("/reports/type")
    public Map<TransactionType, List<Transaction>> getByType() {
        return service.getMapByTransactionType();
    }

    @GetMapping("/reports/product")
    public Map<String, List<Transaction>> getByProduct() {
        return service.getMapByTransactionProduct();
    }
}

