package com.project.expense_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ExpenseDTO {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Category cannot be empty")
    private String category;
}