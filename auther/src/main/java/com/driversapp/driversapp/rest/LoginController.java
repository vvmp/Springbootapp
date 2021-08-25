package com.driversapp.driversapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.driversapp.driversapp.constant.Constants;
import com.driversapp.driversapp.constant.MessageConstants;
import com.driversapp.driversapp.exception.ApplicationException;
import com.driversapp.driversapp.services.UserService;
import com.driversapp.driversapp.view.APIResponseView;
import com.driversapp.driversapp.view.UserLogInView;

@RestController
@RequestMapping(value = "/v1")
public class LoginController {
	@Autowired
	APIResponseView apiResponseView;
	@Autowired
	UserService userService;

	@PostMapping(value = "/user/login")
	public APIResponseView userLogin(@RequestBody UserLogInView userLogin) throws ApplicationException {
		apiResponseView.setPayload(userService.userLogin(userLogin));
		apiResponseView.setStatus(Constants.SUCCESS_STATUS);
		apiResponseView.setMessage(MessageConstants.USER_LOGIN_SUCCESSFULLY);

		return apiResponseView;
	}

}
