package com.example.expensetracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.expensetracker.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
