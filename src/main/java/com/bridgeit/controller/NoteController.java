package com.bridgeit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Note;
import com.bridgeit.model.User;
import com.bridgeit.model.UserResponse;
import com.bridgeit.service.NoteService;
import com.bridgeit.service.Service;
import com.bridgeit.utility.JWT;

@RestController
public class NoteController {

	@Autowired
	NoteService noteService;

	@Autowired
	JWT jwtObject;
	
	@RequestMapping(value = "notes/create", method = RequestMethod.POST)
	public ResponseEntity<UserResponse> create(@RequestBody Note note, HttpServletRequest request) {
		String token = jwtObject.jwtVerify(request.getHeader("accToken"));
		
		UserResponse message=new UserResponse();
		
		if (token != null) {
			noteService.createNote(note, token);
			message.setMessage("Note created Successfully.");
			return new ResponseEntity<UserResponse>(message, HttpStatus.OK);

		} else {
			message.setMessage("Invalid Token");
			return new ResponseEntity<UserResponse>(message, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "notes/delete", method = RequestMethod.POST)
	public ResponseEntity<UserResponse> delete(@RequestBody Note note, HttpServletRequest request) {

		String token = jwtObject.jwtVerify(request.getHeader("accToken"));
		UserResponse message=new UserResponse();
		if (token != null) {
			noteService.deleteNote(note);
			message.setMessage("Note Deleted Successfully.");
			return new ResponseEntity<UserResponse>(message, HttpStatus.OK);

		} else {
			message.setMessage("Invalid Token");
			return new ResponseEntity<UserResponse>(message, HttpStatus.CONFLICT);
		}

	}

	@RequestMapping(value = "notes/update", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody Note note, HttpServletRequest request) {

		String token = jwtObject.jwtVerify(request.getHeader("accToken"));

		if (token != null) {
			noteService.updateNote(note, token);
			return new ResponseEntity<String>("Note Updated Successfully.", HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("Invalid Token", HttpStatus.CONFLICT);
		}

	}

	@RequestMapping(value = "notes/read", method = RequestMethod.POST)
	public ResponseEntity<List<Note>> read(HttpServletRequest request) {

		String token = jwtObject.jwtVerify(request.getHeader("accToken"));

		if (token != null) {
			List<Note> resultNote = noteService.readNote(token);
			return new ResponseEntity<List<Note>>(resultNote, HttpStatus.OK);

		} else {
			Note resultNote =null;
			return null;
		}

	}
	
	@RequestMapping(value = "notes/archive", method = RequestMethod.POST)
	public ResponseEntity<UserResponse> archive(@RequestBody Note note, HttpServletRequest request) {
		int userId = jwtObject.jwtVerifyToken(request.getHeader("accToken"));
		UserResponse message=new UserResponse();
		if (userId != 0) {
			 noteService.archiveNote(note,userId);
			 message.setMessage("Note Archived");
			return new ResponseEntity<UserResponse>(message, HttpStatus.OK);

		} else {
			message.setMessage("Invalid Token");
			return new ResponseEntity<UserResponse>(message, HttpStatus.CONFLICT);
		}

	}
	
	
	
	@RequestMapping(value = "notes/trash", method = RequestMethod.POST)
	public ResponseEntity<UserResponse> trash(@RequestBody Note note, HttpServletRequest request) {
		int userId = jwtObject.jwtVerifyToken(request.getHeader("accToken"));
		UserResponse message=new UserResponse();
		if (userId != 0) {
			 noteService.trashNote(note, userId);
			 message.setMessage("Note Trashed");
			return new ResponseEntity<UserResponse>(message, HttpStatus.OK);
		} else {
			message.setMessage("Invalid Token");
			return new ResponseEntity<UserResponse>(message, HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "notes/pin", method = RequestMethod.POST)
	public ResponseEntity<String> pin(@RequestBody Note note, HttpServletRequest request) {
		int userId = jwtObject.jwtVerifyToken(request.getHeader("accToken"));

		if (userId != 0) {
			 noteService.pinNote(note, userId);
			return new ResponseEntity<String>("success", HttpStatus.OK);

		} else {
		
			return new ResponseEntity<String>("Discarded", HttpStatus.CONFLICT);
		}
	}
	
	
	@RequestMapping(value = "notes/color", method = RequestMethod.POST)
	public ResponseEntity<String> color(@RequestBody Note note, HttpServletRequest request) {

		int userId = jwtObject.jwtVerifyToken(request.getHeader("accToken"));

		if (userId != 0) {
			 noteService.coloerNote(note, userId);
			return new ResponseEntity<String>("success", HttpStatus.OK);

		} else {
		
			return new ResponseEntity<String>("Discarded", HttpStatus.CONFLICT);
		}

		
	}
	
	
	@RequestMapping(value = "notes/reminder", method = RequestMethod.POST)
	public ResponseEntity<String> reminder(@RequestBody Note note, HttpServletRequest request) {

		int userId = jwtObject.jwtVerifyToken(request.getHeader("accToken"));

		if (userId != 0) {
			 noteService.remindNote(note, userId);
			return new ResponseEntity<String>("success", HttpStatus.OK);

		} else {
		
			return new ResponseEntity<String>("Discarded", HttpStatus.CONFLICT);
		}

		
	}
	

}
