package com.bridgeit.service;

import com.bridgeit.model.Note;

public interface NoteService {


		void createNote(Note note,String token);
		Note readNote(Note note);
		void updateNote(Note note,String token);
		void deleteNote(Note note);
	}


