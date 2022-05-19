package com.vinod.jrtp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CITY_MASTER")
@Data
public class CityMaster {

	@Id
	@Column(name="CITY_ID")
	private Integer cityId;
	@Column(name="CITY_NAME")
	private String  cityName;
	@Column(name="STATE_ID")
	private Integer stateId;
}
