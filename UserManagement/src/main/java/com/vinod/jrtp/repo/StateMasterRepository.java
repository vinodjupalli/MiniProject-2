package com.vinod.jrtp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinod.jrtp.entity.StateMaster;

public interface StateMasterRepository extends JpaRepository<StateMaster, Integer> {

	public List<StateMaster> findByCountryId(Integer countryId);
}
