package dev.adrian.expense_tracker.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Expense Tracker API",
                version = "v1",
                description = "Microservicio de gestión de gastos"
        )
)
@Configuration
public class OpenApiConfig {}

