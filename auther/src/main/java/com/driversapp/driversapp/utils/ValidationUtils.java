package com.driversapp.driversapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.driversapp.driversapp.constant.Constants;
import com.driversapp.driversapp.constant.MessageConstants;
import com.driversapp.driversapp.exception.ApplicationException;

public class ValidationUtils {
	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("^[\\._A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	private static final Pattern MOBILE_PATTERN = Pattern.compile("[0-9]{10,20}");

	private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z ]+$");

	private static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");

//	private static final Pattern ALAPHABET_ONLY_PATTERN = Pattern.compile("^[a-zA-Z ]+$");

//	private static final Pattern NUMBER_ONLY_PATTERN = Pattern.compile("[0-9]+");

	private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");

	private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");

	private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]");

	private static final Pattern MIN_AND_MAX_PATTERN = Pattern.compile("^.{8,16}$");

	public static boolean validateEmail(final String email) throws ApplicationException {
		boolean status;
		if (email != null && email.trim().length() <= 50) {
			Matcher matcher = EMAIL_PATTERN.matcher(email);
			if (matcher.matches()) {
				status = true;
			} else {
				status = false;
				throw throwException(MessageConstants.USER_EMAIL_NOT_VALID, Constants.PRECONDITION_FAILED);
			}
		} else {
			status = false;
			throw throwException(MessageConstants.USER_EMAIL_NOT_VALID, Constants.PRECONDITION_FAILED);
		}
		return status;
	}

	public static boolean validateMobileNumber(final String mobileNumber) throws ApplicationException {
		boolean isValid = false;
		if (mobileNumber != null && (mobileNumber.length() <= 20 || mobileNumber.length() > 10)) {
			Matcher matcher = MOBILE_PATTERN.matcher(mobileNumber.trim());
			if (matcher.matches()) {
				isValid = true;
			} else {
				isValid = false;
				throw throwException(MessageConstants.USER_PHONE_NUMBER_NOT_VALID, Constants.PRECONDITION_FAILED);
			}
		}
		return isValid;
	}

	public static boolean validateUsername(final String name) throws ApplicationException {
		boolean isValid = false;
		if (name != null) {
			Matcher matcher = USERNAME_PATTERN.matcher(name.trim());
			if (matcher.matches()) {
				isValid = true;
			} else {
				throw throwException(MessageConstants.USERNAME_NOT_VALID, Constants.PRECONDITION_FAILED);
			}
		}else {
			throw throwException(MessageConstants.USERNAME_NOT_VALID, Constants.PRECONDITION_FAILED);
		}
		
		return isValid;
	}

	public static boolean validateAlphaNumeric(final String text) {
		boolean isValid = false;
		if (text != null) {
			Matcher matcher = ALPHANUMERIC_PATTERN.matcher(text.trim());
			if (matcher.matches()) {
				isValid = true;
			}
		}
		return isValid;
	}

	public static boolean validatePassword(final String password) throws ApplicationException {

		boolean flag = true;

		if (!MIN_AND_MAX_PATTERN.matcher(password).matches()) {
			throw throwException("Password must have alleast 8 character and should not exceed 16 character",
					Constants.PRECONDITION_FAILED);
		}
		if (!UPPERCASE_PATTERN.matcher(password).find()) {
			throw throwException("Password must have atleast one uppercase character", Constants.PRECONDITION_FAILED);
		}
		if (!NUMBER_PATTERN.matcher(password).find()) {
			throw throwException("Password must have atleast one Number", Constants.PRECONDITION_FAILED);
		}
		if (!LOWERCASE_PATTERN.matcher(password).find()) {
			throw throwException("Password must have atleast one lowercase character", Constants.PRECONDITION_FAILED);
		}
		if (!password.matches("\\S+")) {
			throw throwException("Password should not contain spaces", Constants.PRECONDITION_FAILED);
		}
		return flag;

	}

	public static boolean validateObj(Object obj) throws ApplicationException {
		if (obj == null) {
			throw throwException(MessageConstants.REQUEST_OBJECT_NOT_FOUNT, Constants.BAD_REQUEST);
		}
		return true;
	}

	public static ApplicationException throwException(final String message, final long status) {
		return new ApplicationException(status, message, Constants.RESPONSE_FAILURE);
	}
}
