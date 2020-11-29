package com.example.expensetracker.service;

import java.util.ArrayList;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AdminUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("admin", "$2a$10$egmkbLx0yFHzHemPAgERkOVBYXIf6FETB0NQqeHWrNdqWRibj/Toq", new ArrayList<>());
	}

}
