package com.example.expensetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;

@Controller
public class ExpenseContoller {

	private final ExpenseRepository expenseRepository;

	private final UserRepository userRepository;

	public ExpenseContoller(ExpenseRepository expenseRepository, UserRepository userRepository) {
		this.expenseRepository = expenseRepository;
		this.userRepository = userRepository;
	}

	@RequestMapping({ "/users/{id}/expense" })
	public String getExpenses(@PathVariable Long id, Model model) {
		model.addAttribute("expenses", expenseRepository.findByUserUserId(id).orElse(null));
		model.addAttribute("userId", id);
		return "expense";
	}

	@GetMapping
	@RequestMapping({ "/users/{id}/expense/create" })
	public String createExpense(@PathVariable Long id, Model model) {
		model.addAttribute("expense", new Expense());
		model.addAttribute("userId", id);
		return "createExpense";
	}

	@PostMapping
	@RequestMapping({ "/users/{id}/expense/save" })
	public String saveExpense(@PathVariable Long id, @ModelAttribute Expense expense) {
		User user = userRepository.findById(id).get();
		expense.setUser(user);
		expenseRepository.save(expense);
		user.getExpenses().add(expense);
		userRepository.save(user);
		return "redirect:/users/{id}/expense";
	}
}
