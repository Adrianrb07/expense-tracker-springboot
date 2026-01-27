package dev.adrian.expense_tracker.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.adrian.expense_tracker.domain.Expense;
import dev.adrian.expense_tracker.dto.ExpenseRequest;
import dev.adrian.expense_tracker.service.ExpenseService;
import dev.adrian.expense_tracker.web.errors.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ExpenseController.class)
class ExpenseControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    @MockitoBean ExpenseService service;

    @Test
    void create_should_return_400_when_body_is_invalid() throws Exception {
        var invalidJson = """
            { "description": "", "amountMinor": -10, "currency": "EU", "expenseDate": null }
        """;

        mvc.perform(post("/api/v1/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("validation_error"))
                .andExpect(jsonPath("$.details.description").exists())
                .andExpect(jsonPath("$.details.amountMinor").exists())
                .andExpect(jsonPath("$.details.currency").exists())
                .andExpect(jsonPath("$.details.expenseDate").exists());
    }

    @Test
    void create_should_return_201_when_body_is_valid() throws Exception {
        var req = new ExpenseRequest("Supermercado", 2599L, "EUR", LocalDate.parse("2025-08-21"));

        var saved = new Expense();
        saved.setId(UUID.randomUUID());
        saved.setDescription(req.description());
        saved.setAmountMinor(req.amountMinor());
        saved.setCurrency(req.currency());
        saved.setExpenseDate(req.expenseDate());

        when(service.create(any())).thenReturn(saved);

        mvc.perform(post("/api/v1/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.description").value("Supermercado"))
                .andExpect(jsonPath("$.amountMinor").value(2599))
                .andExpect(jsonPath("$.currency").value("EUR"))
                .andExpect(jsonPath("$.expenseDate").value("2025-08-21"));
    }

    @Test
    void update_should_return_404_when_not_found() throws Exception {
        var id = UUID.randomUUID();
        var req = new ExpenseRequest("Cine", 1500L, "EUR", LocalDate.parse("2025-08-21"));

        when(service.update(eq(id), any())).thenThrow(new NotFoundException("Expense not found: " + id));

        mvc.perform(put("/api/v1/expenses/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("not_found"))
                .andExpect(jsonPath("$.message").value("Expense not found: " + id));
    }

    @Test
    void list_should_filter_by_date_range() throws Exception {
        when(service.listByDateRange(LocalDate.parse("2025-08-01"), LocalDate.parse("2025-08-31")))
                .thenReturn(List.of());

        mvc.perform(get("/api/v1/expenses")
                        .param("from", "2025-08-01")
                        .param("to", "2025-08-31"))
                .andExpect(status().isOk());
    }
}
