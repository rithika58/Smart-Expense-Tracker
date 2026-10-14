package com.project.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import com.project.expense_tracker.entity.User;

@Entity
@Table(name = "expenses")
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Category cannot be empty")
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}