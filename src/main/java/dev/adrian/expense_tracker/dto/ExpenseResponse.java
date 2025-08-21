package dev.adrian.expense_tracker.dto;

import java.time.LocalDate;
import java.util.UUID;

public record ExpenseResponse(
        UUID id,
        String description,
        Long amountMinor,
        String currency,
        LocalDate expenseDate
) {}
