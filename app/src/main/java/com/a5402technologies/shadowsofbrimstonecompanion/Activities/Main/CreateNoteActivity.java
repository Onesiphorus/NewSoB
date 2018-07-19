package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        Button btn = findViewById(R.id.btn_cancel);
        EditText et = findViewById(R.id.et_note);
        btn.setText("Delete");
        if (null != oldNote) {
            et.setText(oldNote);
        }

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, NotesActivity.class);
            String addNote = et.getText().toString();
            if(null != oldNote) sobCharacter.removeNote(oldNote);
            sobCharacter.addNote(addNote);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, NotesActivity.class);
            sobCharacter.removeNote(oldNote);
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
        startActivity(intent);
        finish();

    }
}
