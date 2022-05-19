package com.vinod.jrtp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinod.jrtp.binding.UnlockForm;
import com.vinod.jrtp.service.UserManagementService;

@RestController
public class UnlockRestController {
	
	@Autowired
	private UserManagementService service;
	
	@PostMapping("/unlock")
	public String unlockAcc(@RequestBody UnlockForm unlockForm ) {
		return service.unlockAccount(unlockForm);
	}

}
