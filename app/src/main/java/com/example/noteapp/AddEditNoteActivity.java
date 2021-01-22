package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String ID = "com.example.noteapp.ID";
    public static final String TITLE = "com.example.noteapp.TITLE";
    public static final String DESCRIPTION = "com.example.noteapp.DESCRIPTION";
    public static final String PRIORITY = "com.example.noteapp.PRIORITY";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPicker = findViewById(R.id.number_picker_priority);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(20);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(TITLE));
            editTextDescription.setText(intent.getStringExtra(DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPicker.getValue();

        //Prevent the user from entering empty spaces
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert Title and Description.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Send data back to the Activity
        Intent data = new Intent();
        data.putExtra(TITLE, title);
        data.putExtra(DESCRIPTION, description);
        data.putExtra(PRIORITY, priority);

        int id = getIntent().getIntExtra(ID, -1);
        if(id != -1){
            data.putExtra(ID, id);
        }

        //Indicate if the input was successful or not
        setResult(RESULT_OK, data);
        //Close this activity
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}