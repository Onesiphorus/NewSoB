package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class CreateNoteActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    String oldNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        oldNote = getIntent().getStringExtra("note");

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, NotesActivity.class);
            String addNote = findViewById(R.id.et_note).toString();
            if(!(oldNote.isEmpty())) sobCharacter.removeNote(oldNote);
            sobCharacter.addNote(addNote);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, NotesActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
    }
}
