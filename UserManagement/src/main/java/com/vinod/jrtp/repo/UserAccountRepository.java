package com.vinod.jrtp.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.vinod.jrtp.entity.UserAccount;


public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	public UserAccount findByEmailAndPwd(String email, String pwd);

	public UserAccount findByEmail(String email);
}
