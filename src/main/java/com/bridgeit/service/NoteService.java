package com.bridgeit.service;

import java.util.List;

import com.bridgeit.model.Note;

public interface NoteService {


		void createNote(Note note,String token);
		List<Note> readNote(String token);
		void updateNote(Note note,String token);
		void deleteNote(Note note);
		void archiveNote(Note note,int userId);
		void trashNote(Note note,int userId);
		void pinNote(Note note,int userId);
		void coloerNote(Note note,int userId);
		void remindNote(Note note,int userId);
		
	}


