package com.project.expense_tracker.repository;

import com.project.expense_tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.expense_tracker.entity.User;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(String category);
    List<Expense> findByUser(User user);
}