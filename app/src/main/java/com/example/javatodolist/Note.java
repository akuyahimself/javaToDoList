package com.example.javatodolist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String text;
    private int priority;

    private String data;

    public Note(int id, String text, int priority, String data){
        this.id = id;
        this.text = text;
        this.priority = priority;
        this.data = data;
    }

    public int getId(){return id;}
    public String getText(){return text;}
    public int getPriority(){return priority;}
    public String getData(){return data;}

}
