package com.example.expensetracker.service.jpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Set<User> findAll() {
		Set<User> users = new HashSet<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public User findById(Long id) {
		User user = userRepository.findById(id).orElse(null);
		if (user != null) {
			return user;
		} else {
			throw new RuntimeException("User cannot be null");
		}
	}

	@Override
	public User save(User entity) {
		User user = userRepository.save(entity);
		return user;
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

}
