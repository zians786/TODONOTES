package com.bridgeit.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.NoteDao;
import com.bridgeit.model.Note;
import com.bridgeit.model.User;
import com.bridgeit.utility.JWT;

@Component
@Transactional
public class NoteServiceImp implements NoteService {

	@Autowired
	NoteDao noteDao;

	@Autowired
	JWT jwtObject;

	public void createNote(Note note, String token) {
		int userId = Integer.parseInt(token);
		User user = new User();
		user.setUserId(userId);
		note.setUser(user);
		note.setCreatedDate(new Date(System.currentTimeMillis()));
		note.setModifiedDate(new Date(System.currentTimeMillis()));
		noteDao.create(note);

	}

	public void deleteNote(Note note) {
		noteDao.delete(note);
	}

	public void updateNote(Note note, String token) {
		int userId = Integer.parseInt(token);
		User user = new User();
		user.setUserId(userId);
		note.setUser(user);
		note.setModifiedDate(new Date(System.currentTimeMillis()));
		noteDao.update(note);

	}

	public Note readNote(Note note) {
		return noteDao.read(note);
	}

}
