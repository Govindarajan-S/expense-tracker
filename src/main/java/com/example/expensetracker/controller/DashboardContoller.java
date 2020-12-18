package com.example.expensetracker.controller;

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
		return "dashboard";
	}

}
