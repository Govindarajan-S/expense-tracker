package com.example.expensetracker.service.jpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository expenseRepository;

	public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	@Override
	public Set<Expense> findAll() {
		Set<Expense> expenses = new HashSet<>();
		expenseRepository.findAll().forEach(expenses::add);
		return expenses;
	}

	@Override
	public Expense findById(Long id) {
		Expense expense = expenseRepository.findById(id).orElse(null);
		if (expense != null) {
			return expense;
		} else {
			throw new RuntimeException("Expense is not present");
		}
	}

	@Override
	public Expense save(Expense entity) {
		if (entity == null) {
			throw new RuntimeException("Expense should not be null");
		} else {
			Expense expense = expenseRepository.save(entity);
			return expense;
		}
	}

	@Override
	public void deleteById(Long id) {
		expenseRepository.deleteById(id);
	}

	@Override
	public void delete(Expense entity) {
		expenseRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		expenseRepository.deleteAll();
	}

}
