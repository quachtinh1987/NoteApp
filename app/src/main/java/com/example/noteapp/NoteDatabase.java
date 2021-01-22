package com.example.noteapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    //Create NoteDatabase with only  1 thread (synchronized) can access this method at a time.
    public static synchronized NoteDatabase getInstance(Context context){
        //Instantiate the database if we don't already have an instance
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                    //Recreate database if the version number doesn't match the latest schema version
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    //Populate database right when we create it
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabase(instance).execute();
        }
    };

    private static class PopulateDatabase extends AsyncTask<Void, Void, Void>{
        private NoteDao noteDao;

        private PopulateDatabase(NoteDatabase db){
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Note 1", "Description 1", 1));
            noteDao.insert(new Note("Note 2", "Description 2", 2));
            noteDao.insert(new Note("Note 3", "Description 3", 3));
            return null;
        }
    }

}
