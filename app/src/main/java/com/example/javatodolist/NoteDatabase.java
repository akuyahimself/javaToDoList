package com.example.javatodolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance = null;
    private static final String DB_name = "notes_db";

    public static NoteDatabase getInstance(Application application){
        if(instance == null){
            instance = Room.databaseBuilder(application,
                    NoteDatabase.class, DB_name).build();
        }
        return instance;
    }
    public abstract NotesDao notesDao();
}
