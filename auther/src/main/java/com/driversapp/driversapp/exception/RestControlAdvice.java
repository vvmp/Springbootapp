package com.driversapp.driversapp.exception;

import java.util.List;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.driversapp.driversapp.constant.Constants;
import com.driversapp.driversapp.constant.MessageConstants;
import com.driversapp.driversapp.view.APIResponseView;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.gson.Gson;

@RestControllerAdvice
public class RestControlAdvice {

	@ExceptionHandler(PropertyReferenceException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public APIResponseView invalidRequest(PropertyReferenceException exception) {

		APIResponseView responseView = new APIResponseView();
		responseView.setStatus(Constants.INVALID_STATUS);
		responseView.setMessage(exception.getMessage());
		System.out.println(exception.getMessage() + " : " + exception);
		return responseView;
	}

	@ExceptionHandler(InvalidFormatException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public APIResponseView invalidDataException(InvalidFormatException exception) {

		APIResponseView responseView = new APIResponseView();
		responseView.setStatus(Constants.BAD_REQUEST);
		responseView.setMessage(Constants.RESPONSE_INVALID_DATA);
		System.out.println(exception.getMessage() + " : " + exception);
//		log.error(exception.getMessage(), exception);
		return responseView;
	}

	@ExceptionHandler(HttpServerErrorException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<APIResponseView> httpServerError(HttpServerErrorException exception) {
		ResponseEntity<APIResponseView> responseEntity = null;

		APIResponseView apiResponseView = new APIResponseView();
		APIResponseView responseView = new Gson().fromJson(exception.getResponseBodyAsString(), APIResponseView.class);
		apiResponseView.setStatus(responseView.getStatus());
		apiResponseView.setMessage(responseView.getMessage());
		responseEntity = new ResponseEntity<>(apiResponseView, exception.getStatusCode());
		System.out.println(exception.getMessage() + " : " + exception);
//		log.error(apiResponseView.getMessage(),exception);
		return responseEntity;
	}

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<APIResponseView> httpClientError(HttpClientErrorException exception) {

		ResponseEntity<APIResponseView> responseEntity = null;

		APIResponseView responseModel = new APIResponseView();
		APIResponseView model = new Gson().fromJson(exception.getResponseBodyAsString(), APIResponseView.class);
		responseModel.setStatus(model.getStatus());
		responseModel.setMessage(model.getMessage());
		responseEntity = new ResponseEntity<>(responseModel, exception.getStatusCode());
		System.out.println(exception.getMessage() + " : " + exception);
//		log.error(responseModel.getMessage(),exception);
		return responseEntity;
	}

	@ExceptionHandler(ResourceAccessException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public APIResponseView httpUnknownHost(ResourceAccessException exception) {

		APIResponseView model = new APIResponseView();
		model.setStatus(Constants.ERROR_STATUS);
		model.setMessage(MessageConstants.SERVER_ERROR);
		System.out.println(exception.getMessage() + " : " + exception);
//		log.error(model.getMessage(),exception);
		return model;
	}

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<APIResponseView> applicationException(ApplicationException applicationException) {

		ResponseEntity<APIResponseView> entity = null;
		
		APIResponseView model = new APIResponseView();
		model.setStatus(applicationException.getStatus());
		model.setMessage(applicationException.getMessage());
		model.setError(applicationException.getError());
		System.out.println(applicationException.getMessage() + " : " + applicationException);
//		log.warn(applicationException.getMessage(), applicationException);
		
		if (applicationException.getStatus() == 4001) {
			entity = new ResponseEntity<>(model, HttpStatus.UNAUTHORIZED);
		}
		if (applicationException.getStatus() == 4003) {
			entity = new ResponseEntity<>(model, HttpStatus.FORBIDDEN);
		}
		if (applicationException.getStatus() == 4009) {
			entity = new ResponseEntity<>(model, HttpStatus.CONFLICT);
		}
		if (applicationException.getStatus() == 4012) {
			entity = new ResponseEntity<>(model, HttpStatus.PRECONDITION_FAILED);
		}
		if (applicationException.getStatus() == 4022) {
			entity = new ResponseEntity<>(model, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if (applicationException.getStatus() == 5000) {
			entity = new ResponseEntity<>(model, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (applicationException.getStatus() == 4000) {
			entity = new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
		}
		if (applicationException.getStatus() == 4008) {
			entity = new ResponseEntity<>(model, HttpStatus.REQUEST_TIMEOUT);
		}
		
		if (applicationException.getStatus() == 5001) {
			entity = new ResponseEntity<>(model, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return entity;
	}
	
//	@ExceptionHandler(InviteTokenExpiredException.class)
//	public APIResponseView inviteTokenExpiredExp(InviteTokenExpiredException exception,
//	HttpServletResponse httpServletResponse) {
//		APIResponseView model = new APIResponseView();
//		model.setStatus(exception.getStatus());
//		model.setMessage(exception.getMessage());
//		httpServletResponse.setStatus(Constants.TOKEN_TIME_OUT);
//		return model;
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<APIResponseView> handleException(MethodArgumentNotValidException exception) {
		List<FieldError> errors = exception.getBindingResult().getFieldErrors();
		String errorDetails = "";
		if(!errors.isEmpty()) {
			errorDetails = errors.get(0).getDefaultMessage();
		}
		APIResponseView model = new APIResponseView();
		model.setStatus(Constants.UNPROCESSABLE_ENTITY);
		model.setMessage(errorDetails);
		System.out.println(exception.getMessage() + " : " + exception);
		return new ResponseEntity<>(model, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(RedisConnectionFailureException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public APIResponseView redisConnectionException(RedisConnectionFailureException exception) {

		APIResponseView model = new APIResponseView();
		model.setStatus(Constants.ERROR_STATUS);
		model.setMessage(Constants.RESPONSE_FAILURE);
		System.out.println(exception.getMessage() + " : " + exception);
//		log.warn(exception.getMessage(), exception);
		return model;
	}
}

