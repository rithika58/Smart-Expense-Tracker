package com.project.expense_tracker.controller;

import com.project.expense_tracker.entity.Expense;
import com.project.expense_tracker.service.ExpenseService;
import com.project.expense_tracker.dto.ExpenseDTO;
import com.project.expense_tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.project.expense_tracker.entity.User;
import com.project.expense_tracker.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense addExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = new Expense();

        expense.setTitle(expenseDTO.getTitle());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());

        expense.setUser(user);

        return expenseService.addExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return expenseService.getExpensesByUser(user);
    }
    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(@PathVariable String category) {

        return expenseService.getExpensesByCategory(category);
    }
    @GetMapping("/total")
    public Double getTotalExpenses() {

        return expenseService.getTotalExpenses();
    }
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id,
                                 @RequestBody Expense updatedExpense) {
        return expenseService.updateExpense(id,updatedExpense);
    }
    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        return expenseService.deleteExpense(id);
    }
}