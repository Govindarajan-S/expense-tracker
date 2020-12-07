package com.example.expensetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
import com.example.expensetracker.service.ExpenseService;
import com.example.expensetracker.service.UserService;

@Controller
public class ExpenseController {

	private final ExpenseService expenseService;

	private final UserService userService;

	public ExpenseController(ExpenseService expenseService, UserService userService) {
		this.expenseService = expenseService;
		this.userService = userService;
	}

	@RequestMapping({ "/users/{id}/expense" })
	public String getExpenses(@PathVariable Long id, Model model) {
		model.addAttribute("expenses", userService.findById(id).getExpenses());
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
		User user = userService.findById(id);
		expense.setUser(user);
		expenseService.save(expense);
		user.getExpenses().add(expense);
		userService.save(user);
		return "redirect:/users/{id}/expense";
	}

	@GetMapping
	@RequestMapping({ "/users/{id}/expense/{expenseId}/edit" })
	public String getExpenseUpdatePage(@PathVariable Long id, @PathVariable Long expenseId, Model model) {
		model.addAttribute("userId", id);
		model.addAttribute("expense", expenseService.findById(expenseId));
		return "updateExpense";
	}

	@PutMapping
	@RequestMapping({ "/users/{id}/expense/{expenseId}/save" })
	public String updateExpense(@PathVariable Long id, @PathVariable Long expenseId, Expense expense) {
		expenseService.save(expense);
		return "redirect:/users/{id}/expense";
	}

	@GetMapping
	@RequestMapping({ "/users/{id}/expense/{expenseId}/delete" })
	public String doDeleteExpense(@PathVariable Long id, @PathVariable Long expenseId) {
		User user = userService.findById(id);
		user.deleteExpense(expenseService.findById(expenseId));
		expenseService.deleteById(expenseId);
		return "redirect:/users/{id}/expense";
	}

	@GetMapping
	@RequestMapping({ "/users/{id}/expense/search" })
	public String searchExpense(@PathVariable Long id, @RequestParam("description") String description, Model model) {
		model.addAttribute("expenses", expenseService.findByDescription(description));
		return "searchExpense";
	}
}
