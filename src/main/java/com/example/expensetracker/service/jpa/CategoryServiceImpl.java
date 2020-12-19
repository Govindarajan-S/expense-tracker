package com.example.expensetracker.service.jpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.expensetracker.model.Category;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Set<Category> findAll() {
		Set<Category> categorySet = new HashSet<>();
		categoryRepository.findAll().forEach(categorySet::add);
		return categorySet;
	}

	@Override
	public Category findById(Long id) {
		Category category = categoryRepository.findById(id).orElse(null);
		return category;
	}

	@Override
	public Category save(Category entity) {
		Category category = categoryRepository.save(entity);
		return category;
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}

	@Override
	public void update(Long id, Category category) {
		Category updatedCategory = categoryRepository.findById(id).orElse(null);
		if (updatedCategory != null) {
			updatedCategory.setCategoryName(category.getCategoryName());
		}
		categoryRepository.save(updatedCategory);
	}

}
