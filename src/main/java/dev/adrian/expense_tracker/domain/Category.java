package dev.adrian.expense_tracker.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 60)
    private String name;

    public UUID getId() { return id; }
    public String getName() { return name; }

    public void setId(UUID id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}
