package dev.adrian.expense_tracker.service;

import dev.adrian.expense_tracker.domain.Expense;
import dev.adrian.expense_tracker.dto.ExpenseRequest;
import dev.adrian.expense_tracker.repository.ExpenseRepository;
import dev.adrian.expense_tracker.web.errors.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseService {

    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Expense create(ExpenseRequest req) {
        Expense e = new Expense();
        e.setDescription(req.description());
        e.setAmountMinor(req.amountMinor());
        e.setCurrency(req.currency().toUpperCase());
        e.setExpenseDate(req.expenseDate());
        return repo.save(e);
    }

    @Transactional
    public Expense update(UUID id, ExpenseRequest req) {
        Expense e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Expense not found: " + id));
        e.setDescription(req.description());
        e.setAmountMinor(req.amountMinor());
        e.setCurrency(req.currency().toUpperCase());
        e.setExpenseDate(req.expenseDate());
        return repo.save(e);
    }

    @Transactional(readOnly = true)
    public List<Expense> listAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Expense> listByDateRange(LocalDate from, LocalDate to) {
        return repo.findByExpenseDateBetweenOrderByExpenseDateDesc(from, to);
    }

    public Optional<Expense> findById(UUID id) {
        return repo.findById(id);
    }

    @Transactional
    public void delete(UUID id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Expense not found: " + id);
        }
        repo.deleteById(id);
    }
}
