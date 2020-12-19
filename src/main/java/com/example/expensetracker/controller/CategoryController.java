package com.example.expensetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.expensetracker.model.Category;
import com.example.expensetracker.service.CategoryService;

@Controller
@RequestMapping({ "/category" })
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getAllCategory(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		return "category";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCategory(Model model) {
		model.addAttribute("category", new Category());
		return "categoryForm";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute Category category) {
		categoryService.save(category);
		return "redirect:/category";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String getEditCategory(@PathVariable Long id, Model model) {
		model.addAttribute("category", categoryService.findById(id));
		return "categoryUpdateForm";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String editCategory(@PathVariable Long id, @ModelAttribute Category category, Model model) {
		categoryService.update(id, category);
		model.addAttribute("categories", categoryService.findAll());
		return "redirect:/category";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable Long id, Model model) {
		categoryService.deleteById(id);
		model.addAttribute("categories", categoryService.findAll());
		return "redirect:/category";
	}
}
