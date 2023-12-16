package com.example.javatodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddNoteActivity extends AppCompatActivity {
    private Handler handler = new Handler(Looper.getMainLooper());

    private EditText editTextNote;
    private EditText editTextData;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;

    private Button buttonSave;
    private NoteDatabase noteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteDatabase = NoteDatabase.getInstance(getApplication());
        initViews();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 saveNote();
            }
        });
    }
    private int getPriority(){
        int priority = 2;
        if(radioButtonLow.isChecked()){
            priority = 0;
        } else if(radioButtonMedium.isChecked()){
            priority = 1;
        }
        return priority;
    }
    private void saveNote(){
        String text = editTextNote.getText().toString().trim();
        String data = editTextData.getText().toString().trim();
        int priority=getPriority();

        Note note = new Note(0, text, priority, " ");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                noteDatabase.notesDao().addNote(note);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                });
            }
        });
        thread.start();
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, AddNoteActivity.class);
        return intent;
    }
    private void initViews(){
        editTextNote=findViewById(R.id.editTextNote);
        editTextData=findViewById(R.id.editTextData);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);

        buttonSave =findViewById(R.id.buttonSave);
    }
}