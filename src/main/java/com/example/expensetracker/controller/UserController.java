package com.example.expensetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.expensetracker.repository.UserRepository;

@Controller
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping({ "", "/", "/users", "/users.html" })
	public String getUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "users";
	}
}
