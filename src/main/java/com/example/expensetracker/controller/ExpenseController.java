package com.example.expensetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;

@Controller
public class ExpenseController {

	private final ExpenseRepository expenseRepository;

	private final UserRepository userRepository;

	public ExpenseController(ExpenseRepository expenseRepository, UserRepository userRepository) {
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

	@GetMapping
	@RequestMapping({ "/users/{id}/expense/{expenseId}/edit" })
	public String getExpenseUpdatePage(@PathVariable Long id, @PathVariable Long expenseId, Model model) {
		model.addAttribute("userId", id);
		model.addAttribute("expense", expenseRepository.findById(expenseId).get());
		return "updateExpense";
	}

	@PutMapping
	@RequestMapping({ "/users/{id}/expense/{expenseId}/save" })
	public String updateExpense(@PathVariable Long id, @PathVariable Long expenseId, Expense expense) {
		expenseRepository.save(expense);
		return "redirect:/users/{id}/expense";
	}

	@DeleteMapping
	@RequestMapping({ "/users/{id}/expense/{expenseId}/delete" })
	public String doDeleteExpense(@PathVariable Long id, @PathVariable Long expenseId) {
		User user = userRepository.findById(id).get();
		user.getExpenses().remove(expenseRepository.findById(expenseId).get());
		expenseRepository.deleteById(expenseId);
		return "redirect:/users/{id}/expense";
	}
}
