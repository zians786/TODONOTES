package com.bridgeit.dao;

import com.bridgeit.model.Note;

public interface NoteDao {
	
 void create(Note note);
 void update(Note note);
 Note read(Note note);
 void delete(Note note);
 void archive(Note note,int userId);
 void trash(Note note,int userId);
 void pin(Note note,int userId);
 void color(Note note,int userId);
 void remind(Note note,int userId);
 
}
