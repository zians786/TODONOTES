package com.bridgeit.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.User;
import com.bridgeit.model.UserResponse;
import com.bridgeit.service.Service;
import com.bridgeit.validation.Validation;

/**
 * @author bridgeit
 *
 */

@RestController
public class UserController {

	@Autowired
	Service service;
	@Autowired
	Validation validate;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcome() {
		return "Welcome";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public ResponseEntity<UserResponse> registration(@RequestBody User user) {
		UserResponse message=new UserResponse();
		
		if (validate.userValidate(user)) {
			 message.setMessage(service.registrationValidate(user));
			return new ResponseEntity<UserResponse>(message, HttpStatus.OK);
		} else {
			message.setMessage("Please Enter Correct Values...");
			return new ResponseEntity<UserResponse>(message, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ResponseEntity<UserResponse> login(@RequestBody User user, HttpServletRequest request) {
		UserResponse response = new UserResponse();
		String token = service.loginValidate(user);
		if (token != null) {

			HttpSession session = request.getSession();
			response.setMessage("Welcome " + user.getUserName());
			response.setToken(token);
			return new ResponseEntity<UserResponse>(response, HttpStatus.OK);

		} else {
			response.setMessage("Invalid Username or Password");
			return new ResponseEntity<UserResponse>(response, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/activate/{jwt:.+}", method = RequestMethod.GET)
	public ResponseEntity<String> activation(@PathVariable String jwt) {
		String status = service.verifyToken(jwt);
		return new ResponseEntity<String>(status, HttpStatus.OK);

	}

	@RequestMapping(value = { "/forgot" }, method = RequestMethod.POST)
	public ResponseEntity<String> forgot(@RequestBody User user) {

		String message = service.forgetPassword(user.getEmail());
		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@RequestMapping(value = "/reset/{jwt:.+}", method = RequestMethod.POST)
	public ResponseEntity<String> resetPassword(@PathVariable String jwt, @RequestBody User user) {
		String status = service.resetPassword(jwt, user);
		return new ResponseEntity<String>(status, HttpStatus.OK);

	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.POST)
	public ResponseEntity<String> logout(HttpServletRequest request) {
		String message = "No Active Session";
		HttpSession session = request.getSession();
		if (session.isNew()) {
			return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
		}
		session.invalidate();

		message = "Logout Successfully...";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
