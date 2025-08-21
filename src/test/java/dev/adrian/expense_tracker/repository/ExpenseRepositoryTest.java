package dev.adrian.expense_tracker.repository;

import dev.adrian.expense_tracker.domain.Expense;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // usa H2 en memoria
class ExpenseRepositoryTest {

    @Autowired
    ExpenseRepository repo;

    @Test
    void should_find_by_date_range_desc() {
        // given
        repo.save(expense("A", 1000L, "EUR", LocalDate.parse("2025-08-10")));
        repo.save(expense("B", 2000L, "EUR", LocalDate.parse("2025-08-20")));
        repo.save(expense("C", 3000L, "EUR", LocalDate.parse("2025-09-05")));

        // when
        var from = LocalDate.parse("2025-08-01");
        var to   = LocalDate.parse("2025-08-31");
        List<Expense> result = repo.findByExpenseDateBetweenOrderByExpenseDateDesc(from, to);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getDescription()).isEqualTo("B");
        assertThat(result.get(1).getDescription()).isEqualTo("A");
    }

    private Expense expense(String desc, Long amountMinor, String currency, LocalDate date) {
        var e = new Expense();
        e.setDescription(desc);
        e.setAmountMinor(amountMinor);
        e.setCurrency(currency);
        e.setExpenseDate(date);
        return repo.save(e);
    }
}
