package com.example.noteapp;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Insert
    void insert(Note note);

    //Delete all notes from the note_table
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    //Query SQL Lite database to retrieve all notes from the note_table
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
