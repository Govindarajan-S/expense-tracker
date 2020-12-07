package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;

public interface ExpenseService extends CrudService<Expense, Long> {

	Iterable<Expense> findByDescription(String description);

}
