package com.bridgeit.service;

import java.util.Date;
import java.util.List;

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
		Date date=new Date();
		note.setCreatedDate(date);
		note.setModifiedDate(date);
		noteDao.create(note);

	}

	public void deleteNote(Note note) {
		noteDao.delete(note);
	}

	public void updateNote(Note note, String token) {
		int userId = Integer.parseInt(token);
		
		/*Note note1=noteDao.read(note);
		note1.setTitle(note.getTitle());
		note1.setDescription(note.getDescription());
		note1.setImage(note.getImage());
		Date date=new Date();
		note1.setModifiedDate(date);*/
		User user = new User();
		user.setUserId(userId);
		note.setUser(user);
		Date date=new Date();
		
		note.setModifiedDate(date);
		noteDao.update(note);

	}

	public List<Note> readNote(String token) {
		User user=new User();
		int id=Integer.parseInt(token);
		user.setUserId(id);
		
		return noteDao.read(user);
	}

	@Override
	public void archiveNote(Note note,int userId) {
		noteDao.archive(note, userId);
	}

	@Override
	public void trashNote(Note note,int userId) {
		noteDao.trash(note, userId);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pinNote(Note note,int userId) {
		noteDao.pin(note, userId);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coloerNote(Note note,int userId) {
		noteDao.color(note, userId);
	}

	@Override
	public void remindNote(Note note, int userId) {
		noteDao.remind(note, userId);
	}

}
