package com.driversapp.driversapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.driversapp.driversapp.entity.Authentication;
@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication,Long> {
	Authentication findByUserNameAndPassword(String userName, String password);
	

}
