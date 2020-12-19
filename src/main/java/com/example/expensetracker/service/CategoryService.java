package com.example.expensetracker.service;

import com.example.expensetracker.model.Category;

public interface CategoryService extends CrudService<Category, Long> {

	void update(Long id, Category category);

}
