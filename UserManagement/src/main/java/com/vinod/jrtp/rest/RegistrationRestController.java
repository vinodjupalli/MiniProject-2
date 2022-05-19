package com.vinod.jrtp.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinod.jrtp.binding.UserAccountForm;
import com.vinod.jrtp.service.UserManagementService;

@RestController
public class RegistrationRestController {
	@Autowired
	private UserManagementService service;

	@GetMapping("/email/{emailId}")
	public String emailCheck(@PathVariable("emailId") String emailId) {
		return service.emailCheck(emailId);
	}

	@GetMapping("/countries")
	public Map<Integer, String> getCountries() {
		return service.loadCountries();
	}

	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getStates(@PathVariable("countryId") Integer countryId) {
		return service.loadStates(countryId);

	}

	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable("stateId") Integer stateId) {
		return service.loadCities(stateId);

	}
	
	@PostMapping("/user")
	public String registerUser(@RequestBody UserAccountForm userAccountForm) {
		return service.registerUser(userAccountForm);
	}

}
