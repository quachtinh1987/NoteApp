package com.example.noteapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "note_table")
public class Note {
    //Variable "id" will be auto-generated
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private int priority;

    public Note(String title, String description, int priority) {
        this.description = description;
        this.priority = priority;
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
