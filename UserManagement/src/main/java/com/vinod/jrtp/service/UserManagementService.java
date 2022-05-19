package com.vinod.jrtp.service;

import java.util.Map;

import com.vinod.jrtp.binding.LoginForm;
import com.vinod.jrtp.binding.UnlockForm;
import com.vinod.jrtp.binding.UserAccountForm;

public interface UserManagementService {
	public String login(LoginForm loginForm);

	public String emailCheck(String emailId);

	public Map<Integer, String> loadCountries();

	public Map<Integer, String> loadStates(Integer countryId);

	public Map<Integer, String> loadCities(Integer stateId);

	public String registerUser(UserAccountForm userAccountForm);

	public String unlockAccount(UnlockForm unlockForm);

	public String forgotPwd(String emailId);
}
