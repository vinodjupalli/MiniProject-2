package com.vinod.jrtp.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserAccountForm {

private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Long phno;
	
	private LocalDate dob;
	
	private String gender;
	
	private Integer cityId;
	
	private Integer stateId;
	
	private Integer countryId;
}
