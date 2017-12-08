package com.bridgeit.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.User;
import com.bridgeit.model.UserResponse;
import com.bridgeit.service.Service;
import com.bridgeit.utility.FacebookConnection;
import com.bridgeit.utility.GoogleConnection;
import com.bridgeit.utility.JWT;
import com.fasterxml.jackson.databind.JsonNode;



@RestController
public class SocialController {
	
	@Autowired
	FacebookConnection fbconnection;
	@Autowired
	GoogleConnection googleConnection;

	@Autowired
	Service userService;
	
	
	@RequestMapping(value="social/fbLogin",method=RequestMethod.GET)
	public void facebookLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String uId=UUID.randomUUID().toString();
		request.getSession().setAttribute("STATE",uId);
		String fbLoginURL=fbconnection.getFacebookURL(uId);
		response.sendRedirect(fbLoginURL);
		
	}
	
	
	@RequestMapping(value="social/fbConnect",method=RequestMethod.GET)
	public ResponseEntity<UserResponse> facebookConnect(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String userSessionState=(String) request.getSession().getAttribute("STATE");
		String fbState=request.getParameter("state");
		
		if(userSessionState==null || !userSessionState.equals(fbState)){
			response.sendRedirect("socail/fbLogin");
		}
		UserResponse message=new UserResponse();
		String authCode=request.getParameter("code");
		String fbAccessToken=fbconnection.getfbAccessToken(authCode);
		
		JsonNode profile = fbconnection.getUserProfile(fbAccessToken);
		String token=userService.registerSocialAccountUser(profile);
		message.setToken(token);
		response.sendRedirect("dummy");
	
		
		return new ResponseEntity<UserResponse>(message, HttpStatus.OK);
		}
	
	
	
	
	@RequestMapping(value="social/googleLogin",method=RequestMethod.GET)
	public void googleConnection(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String unid=UUID.randomUUID().toString();
		request.getSession().setAttribute("STATE", unid);

		String googleLogInURL=googleConnection.getGoogleURL(unid);


		response.sendRedirect(googleLogInURL);
		return;
	}

	@RequestMapping(value="social/connectGoogle",method=RequestMethod.GET)
	public String redirectFromGoogle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sessionState = (String) request.getSession().getAttribute("STATE");
		String googlestate = request.getParameter("state");

		if (sessionState == null || !sessionState.equals(googlestate)) {

			response.sendRedirect("social/googleLogin");

		}
		String error = request.getParameter("error");

		// change this to the front end homepage address

		if (error != null && error.trim().isEmpty()) {

			response.sendRedirect("userlogin");

		}

		String authCode=request.getParameter("code");
	
		String googleaccessToken = googleConnection.getAccessToken(authCode);
	
		JsonNode profile = googleConnection.getUserProfile(googleaccessToken);


		String message=userService.registerSocialAccountUser(profile);
	
		return message;

	

	}	

}


