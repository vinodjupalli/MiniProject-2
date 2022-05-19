package com.vinod.jrtp.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinod.jrtp.binding.LoginForm;
import com.vinod.jrtp.binding.UnlockForm;
import com.vinod.jrtp.binding.UserAccountForm;
import com.vinod.jrtp.entity.CityMaster;
import com.vinod.jrtp.entity.CountryMaster;
import com.vinod.jrtp.entity.StateMaster;
import com.vinod.jrtp.entity.UserAccount;
import com.vinod.jrtp.repo.CityMasterRepository;
import com.vinod.jrtp.repo.CountryMasterRepository;
import com.vinod.jrtp.repo.StateMasterRepository;
import com.vinod.jrtp.repo.UserAccountRepository;
import com.vinod.jrtp.service.UserManagementService;
import com.vinod.jrtp.utils.EmailUtils;

@Service
public class UserManagementServiceImpl implements UserManagementService  {

	@Autowired
	private UserAccountRepository userRepo;
	@Autowired
	private CountryMasterRepository countryRepo;
	@Autowired
	private StateMasterRepository stateRepo;
	@Autowired
	private CityMasterRepository cityRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	@Override
	public String login(LoginForm loginForm) {
		
		UserAccount entity = userRepo.findByEmailAndPwd(loginForm.getEmail(), loginForm.getPwd());

		if (entity == null) {
			return "InvalidCredentials";
		}
		if (entity != null && entity.getAccStatus().equals("LOCKED")) {
			return "Your Account Is Locked";
		}
		return "Welcome to Ashok IT….";

	}

	@Override
	public String emailCheck(String emailId) {
		
		UserAccount entity = userRepo.findByEmail(emailId);
		if (entity == null) {
			return "Email Validation Successfully";
		}
		return "Already your emailId is Registered";
	}

	@Override
	public Map<Integer, String> loadCountries() {
		
		List<CountryMaster> countries = countryRepo.findAll();

		Map<Integer, String> countryMap = new HashMap();

		for (CountryMaster country : countries) {
			countryMap.put(country.getCountryId(), country.getCountryName());
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStates(Integer countryId) {
		
		List<StateMaster> states = stateRepo.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap();

		for (StateMaster state : states) {
			stateMap.put(state.getStateId(), state.getStateName());
		}
		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {
		
		List<CityMaster> cities = cityRepo.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap();

		for (CityMaster city : cities) {
			cityMap.put(city.getCityId(), city.getCityName());
		}
		return cityMap;
	}

	@Override
	public String registerUser(UserAccountForm userAccountForm) {
		
		UserAccount entity = new UserAccount();
		BeanUtils.copyProperties(userAccountForm, entity);

		entity.setAccStatus("LOCKED");
		entity.setPwd(generateRandomPassword());
		UserAccount savedEntity = userRepo.save(entity);

		String email = userAccountForm.getEmail();
		String subject = "User Registration-Ashok IT";

		String fileName = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
		String body = readMailBodyContent(fileName, entity);
		boolean isSent = emailUtils.sendEmail(email, subject, body);
		if (savedEntity.getUserId() != null && isSent) {
			return "SUCCESS";
		}

		return "FAIL";
	}

	@Override
	public String unlockAccount(UnlockForm unlockForm) {
		
		if (!unlockForm.getNewPwd().equals(unlockForm.getConfirmNewPwd())) {
			return "Password and Confirm Password should be same";
		}

		UserAccount entity = userRepo.findByEmailAndPwd(unlockForm.getEmail(), unlockForm.getTempPwd());

		if (entity == null) {
			return "Incorrect temporary passworrd";
		}
		entity.setPwd(unlockForm.getNewPwd());
		entity.setAccStatus("UNLOCKED");
		userRepo.save(entity);

		return "Account unlocked, please proceed with login";
	}

	@Override
	public String forgotPwd(String emailId) {
		
		UserAccount entity = userRepo.findByEmail(emailId);
		if (entity == null) {
			return "Invalid Email Id";
		}
		String fileName = "RECOVER-PASSWORD-EMAIL-BODY-TEMPLATE.txt";
		String body = readMailBodyContent(fileName, entity);
		String subject = "Recover Password-ASHOK IT";
		boolean isSent = emailUtils.sendEmail(emailId, subject, body);

		if (isSent) {
			return "Password sent to registered mail";
		}
		return "ERROR";
	}
	public String generateRandomPassword() {

		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 6;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	private String readMailBodyContent(String fileName, UserAccount entity) {
		String mailBody = null;
		try {
			StringBuilder sb = new StringBuilder();
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			mailBody = sb.toString();
			mailBody = mailBody.replace("{FNAME}", entity.getFirstName());
			mailBody = mailBody.replace("{LNAME}", entity.getLastName());
			mailBody = mailBody.replace("{TEMP-PWD}", entity.getPwd());
			mailBody = mailBody.replace("{EMAIL}", entity.getEmail());

			br.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}
}
