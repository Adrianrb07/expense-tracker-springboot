package dev.adrian.expense_tracker.service;

import dev.adrian.expense_tracker.domain.Expense;
import dev.adrian.expense_tracker.dto.ExpenseRequest;
import dev.adrian.expense_tracker.repository.ExpenseRepository;
import dev.adrian.expense_tracker.web.errors.NotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    private final ExpenseRepository repo = mock(ExpenseRepository.class);
    private final ExpenseService service = new ExpenseService(repo);

    @Test
    void update_should_throw_NotFound_when_missing() {
        var id = UUID.randomUUID();
        when(repo.findById(id)).thenReturn(Optional.empty());

        var req = new ExpenseRequest("X", 100L, "EUR", LocalDate.parse("2025-08-20"));

        assertThatThrownBy(() -> service.update(id, req))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Expense not found");

        verify(repo, never()).save(any());
    }

    @Test
    void create_should_uppercase_currency_and_save() {
        var req = new ExpenseRequest("Y", 200L, "eur", LocalDate.parse("2025-08-20"));
        when(repo.save(any(Expense.class))).thenAnswer(inv -> inv.getArgument(0));

        var saved = service.create(req);

        verify(repo, times(1)).save(any(Expense.class));
        assert "EUR".equals(saved.getCurrency());
    }
}
