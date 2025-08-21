package dev.adrian.expense_tracker.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 160)
    private String description;

    // Guarda en céntimos para evitar problemas de decimales
    @Column(nullable = false)
    private Long amountMinor;

    @Column(nullable = false, length = 3)
    private String currency; // "EUR", "USD", ...

    @Column(nullable = false)
    private LocalDate expenseDate;

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Long getAmountMinor() {
        return amountMinor;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmountMinor(Long amountMinor) {
        this.amountMinor = amountMinor;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }
}
