package com.bridgeit.service;

import com.bridgeit.model.Note;

public interface NoteService {


		void createNote(Note note,String token);
		Note readNote(Note note);
		void updateNote(Note note,String token);
		void deleteNote(Note note);
		void archiveNote(Note note,int userId);
		void trashNote(Note note,int userId);
		void pinNote(Note note,int userId);
		void coloerNote(Note note,int userId);
		void remindNote(Note note,int userId);
		
	}


