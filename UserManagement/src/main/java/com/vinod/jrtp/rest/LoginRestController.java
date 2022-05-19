package com.vinod.jrtp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinod.jrtp.binding.LoginForm;
import com.vinod.jrtp.service.UserManagementService;

@RestController
public class LoginRestController {
	@Autowired
	private UserManagementService service;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		return service.login(loginForm);
	}
}
