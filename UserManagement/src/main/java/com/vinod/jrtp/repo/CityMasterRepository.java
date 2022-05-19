package com.vinod.jrtp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinod.jrtp.entity.CityMaster;

public interface CityMasterRepository extends JpaRepository<CityMaster, Integer> {
	public List<CityMaster> findByStateId(Integer stateId);
}
