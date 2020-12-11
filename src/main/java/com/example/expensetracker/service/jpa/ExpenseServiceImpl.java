package com.example.expensetracker.service.jpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository expenseRepository;

	private final SessionFactory sessionFactory;

	public ExpenseServiceImpl(ExpenseRepository expenseRepository, SessionFactory sessionFactory) {
		this.expenseRepository = expenseRepository;
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Set<Expense> findAll() {
		Set<Expense> expenses = new HashSet<>();
		expenseRepository.findAll().forEach(expenses::add);
		return expenses;
	}

	@Override
	public Expense findById(Long id) {
		Expense expense = expenseRepository.findById(id).orElse(null);
		if (expense != null) {
			return expense;
		} else {
			throw new RuntimeException("Expense is not present");
		}
	}

	@Override
	public List<Expense> findByDescription(String description) {
		List<Expense> expenseList = new ArrayList<>();
		List<Expense> searchList = new ArrayList<>();
		expenseRepository.findAll().forEach(expenseList::add);
		expenseList.stream().filter(expense -> expense.getDescription().startsWith(description))
				.forEach(searchList::add);
		return searchList;
	}

	@Override
	public Expense save(Expense entity) {
		if (entity == null) {
			throw new RuntimeException("Expense should not be null");
		} else {
			Expense expense = expenseRepository.save(entity);
			return expense;
		}
	}

	@Override
	public void deleteById(Long id) {
		expenseRepository.deleteById(id);
	}

	@Override
	public void delete(Expense entity) {
		expenseRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		expenseRepository.deleteAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Expense> getExpenseBetween(LocalDate fromDate, LocalDate toDate) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		List<Expense> expenseBetweenFromDateAndToDate = session
				.createQuery("from Expense where dateOfExpense between :fromDate and :toDate")
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate).list();
		session.getTransaction().commit();
		session.close();
		return expenseBetweenFromDateAndToDate;
	}

}
