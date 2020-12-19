package com.example.expensetracker.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.expensetracker.service.ExpenseService;

@Controller
public class DashboardContoller {

	private final ExpenseService expenseService;

	public DashboardContoller(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@RequestMapping({ "", "/", "/dashboard" })
	public String viewDashboard(Model model) {
		model.addAttribute("currentWeekExpense", expenseService.getCurrentWeekExpense());
		model.addAttribute("currentMonthExpense", expenseService.getCurrentMonthExpense());
		model.addAttribute("userId", 1);
		model.addAttribute("thisMonth", LocalDate.now().minusMonths(1));
		model.addAttribute("thisWeek", LocalDate.now().minusWeeks(1));
		model.addAttribute("today", LocalDate.now());
		return "dashboard";
	}

}
