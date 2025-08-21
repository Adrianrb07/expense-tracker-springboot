package dev.adrian.expense_tracker.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ExpenseRequest(
        @NotBlank @Size(max = 160) String description,
        @NotNull @Positive Long amountMinor,
        @NotBlank @Size(min = 3, max = 3) String currency, // ISO-4217
        @NotNull LocalDate expenseDate
) {}
