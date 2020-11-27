package com.example.expensetracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.expensetracker.model.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

	Optional<List<Expense>> findByUserUserId(Long id);
}
