package com.bridgeit.service;

import com.bridgeit.model.User;
import com.fasterxml.jackson.databind.JsonNode;

public interface Service {
	

	String registrationValidate(User user);
		
	String verifyToken(String token);
	
	String loginValidate(User user);
	
	String forgetPassword(String email);
	
	String resetPassword(String token, User user);
	
	String registerSocialAccountUser(JsonNode profile);
	
	User getUser(String userName);
	
	User getUserInfo(String token);
}
