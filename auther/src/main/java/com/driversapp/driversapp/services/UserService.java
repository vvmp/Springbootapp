package com.driversapp.driversapp.services;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driversapp.driversapp.constant.Constants;
import com.driversapp.driversapp.entity.Authentication;
import com.driversapp.driversapp.exception.ApplicationException;
import com.driversapp.driversapp.repository.AuthenticationRepository;
import com.driversapp.driversapp.view.UserLogInView;

import java.util.Base64;



@Service
public class UserService {
	@Autowired
	AuthenticationRepository authentication;
	

	public String userLogin(UserLogInView userLogin) throws ApplicationException {
		//TODO: 
		Authentication validation =authentication.findByUserNameAndPassword(userLogin.getUserName(),userLogin.getPassword());
		if(validation == null || validation.getUserId() ==null) {
			throw throwException("unauthorized",Constants.UNAUTHORIZED);
		}	
		return generateNewToken();
	}
	
	
	public static String generateNewToken() {
		 SecureRandom secureRandom = new SecureRandom(); 
		 Base64.Encoder base64Encoder = Base64.getUrlEncoder();
	    byte[] randomBytes = new byte[12];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}
	
	public ApplicationException throwException(final String message, final long status) {
		return new ApplicationException(status, message, Constants.RESPONSE_FAILURE);
	}
	
	
}
