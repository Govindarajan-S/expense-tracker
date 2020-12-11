package com.example.expensetracker.service;

import java.time.LocalDate;

import com.example.expensetracker.model.Expense;

public interface ExpenseService extends CrudService<Expense, Long> {

	Iterable<Expense> findByDescription(String description);

	Iterable<Expense> getExpenseBetween(LocalDate fromDate, LocalDate toDate);

}
