package com.project.expense_tracker.service;

import com.project.expense_tracker.entity.Expense;
import com.project.expense_tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.expense_tracker.entity.User;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }
    public Expense updateExpense(Long id, Expense updatedExpense) {

        Expense expense = expenseRepository.findById(id).orElseThrow();

        expense.setTitle(updatedExpense.getTitle());
        expense.setAmount(updatedExpense.getAmount());
        expense.setCategory(updatedExpense.getCategory());

        return expenseRepository.save(expense);
    }

    public String deleteExpense(Long id) {

        expenseRepository.deleteById(id);

        return "Expense deleted successfully!";
    }
    public List<Expense> getExpensesByUser(User user) {

        return expenseRepository.findByUser(user);
    }
    public Double getTotalExpenses() {

        List<Expense> expenses = expenseRepository.findAll();

        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }
}