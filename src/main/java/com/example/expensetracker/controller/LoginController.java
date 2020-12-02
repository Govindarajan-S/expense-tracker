package com.example.expensetracker.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.expensetracker.model.AuthenticationRequest;
import com.example.expensetracker.util.JwtTokenUtil;

@Controller
public class LoginController {

	private final AuthenticationManager authenticationManager;

	private final UserDetailsService userDetailService;

	private final JwtTokenUtil jwtTokenUtil;

	public LoginController(AuthenticationManager authenticationManager, UserDetailsService userDetailService,
			JwtTokenUtil jwtTokenUtil) {
		this.authenticationManager = authenticationManager;
		this.userDetailService = userDetailService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		model.addAttribute("authenticationRequest", new AuthenticationRequest());
		return "login";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String doLogin(@ModelAttribute AuthenticationRequest authenticationRequest, HttpServletResponse response)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwtToken = jwtTokenUtil.generateToken(userDetails);
		Cookie cookie = new Cookie("token", jwtToken);
		cookie.setMaxAge(1 * 24 * 60 * 60);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		return "redirect:/users";
	}

}
