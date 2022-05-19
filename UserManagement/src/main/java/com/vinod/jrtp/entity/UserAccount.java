package com.vinod.jrtp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="USER_ACCOUNT")
@Data
public class UserAccount {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "USER_PWD")
	private String pwd;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "USER_EMAIL",unique=true)
	private String email;
	@Column(name = "USER_MOBILE")
	private Long phno;
	@Column(name = "DOB")
	private LocalDate dob;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "CITY_ID")
	private Integer cityId;
	@Column(name = "STATE_ID")
	private Integer stateId;
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	@Column(name = "ACC_STATUS")
	private String accStatus;
	@Column(name = "CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(name = "UPDATE_DATE", insertable = false)
	private LocalDate updatedDate;
}
