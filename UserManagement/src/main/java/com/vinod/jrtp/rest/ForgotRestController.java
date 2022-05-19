package com.vinod.jrtp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vinod.jrtp.service.UserManagementService;

@RestController
public class ForgotRestController {
	@Autowired
	private UserManagementService service;
	
	@GetMapping("/forgotpwd/{emailId}")
	public String forgotPwd(@PathVariable String emailId) {
		return service.forgotPwd(emailId);
	}
}
