package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_character).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, CharacterActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_settings).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
