package com.example.expensetracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.expensetracker.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
