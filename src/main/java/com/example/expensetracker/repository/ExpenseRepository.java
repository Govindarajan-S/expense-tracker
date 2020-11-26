package com.example.expensetracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.expensetracker.model.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

}
