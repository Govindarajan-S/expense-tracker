package com.example.expensetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.expensetracker.repository.ExpenseRepository;

@Controller
public class ExpenseContoller {

	private final ExpenseRepository expenseRepository;

	public ExpenseContoller(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	@RequestMapping({ "/users/{id}/expense" })
	public String getExpenses(@PathVariable Long id, Model model) {
		model.addAttribute("expenses", expenseRepository.findByUserUserId(id).orElse(null));

		return "expense";
	}
}
