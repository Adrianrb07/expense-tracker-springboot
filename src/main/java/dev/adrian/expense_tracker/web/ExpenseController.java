package dev.adrian.expense_tracker.web;

import dev.adrian.expense_tracker.domain.Expense;
import dev.adrian.expense_tracker.dto.ExpenseRequest;
import dev.adrian.expense_tracker.dto.ExpenseResponse;
import dev.adrian.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Expenses", description = "CRUD de gastos")
@Validated
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @Operation(summary = "Crear gasto")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponse create(@Valid @RequestBody ExpenseRequest req) {
        return toResponse(service.create(req));
    }

    @Operation(summary = "Listar todos los gastos")
    @GetMapping("/all")
    public List<ExpenseResponse> listAll() {
        return service.listAll().stream().map(this::toResponse).toList();
    }

    @Operation(summary = "Listar gastos por rango de fechas")
    @GetMapping
    public List<ExpenseResponse> list(// Todo: analizar este método y cambiar nombre
                                      @RequestParam @NotNull String from,
                                      @RequestParam @NotNull String to) {

        return service.listByDateRange(LocalDate.parse(from), LocalDate.parse(to))
                .stream().map(this::toResponse).toList();

    }

    @Operation(summary = "Obtener gasto por ID")
    @GetMapping("/{id}")
    public ExpenseResponse getById(@PathVariable UUID id) {
        Expense e = service.listAll().stream()
                .filter(exp -> exp.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Expense not found: " + id));
        return toResponse(e);
    }

    @Operation(summary = "Actualizar gasto por ID")
    @PutMapping("/{id}")
    public ExpenseResponse update(@PathVariable UUID id, @Valid @RequestBody ExpenseRequest req) {
        return toResponse(service.update(id, req));
    }

    @Operation(summary = "Eliminar gasto por ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    private ExpenseResponse toResponse(Expense e) {
        return new ExpenseResponse(
                e.getId(),
                e.getDescription(),
                e.getAmountMinor(),
                e.getCurrency(),
                e.getExpenseDate()
        );
    }
}
