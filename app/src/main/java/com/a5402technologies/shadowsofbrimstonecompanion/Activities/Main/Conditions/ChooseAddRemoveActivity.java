package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Conditions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class ChooseAddRemoveActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_add_remove);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        String type = getIntent().getStringExtra("condition_type");

        findViewById(R.id.btn_add).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, AddOrRemoveConditionActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("condition_type", type);
            intent.putExtra("action", "add");
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_remove).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, AddOrRemoveConditionActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("condition_type", type);
            intent.putExtra("action", "remove");
            startActivity(intent);
            finish();
        });

    }

    public void onBackPressed() {
        Intent intent = new Intent(this, ConditionTypeActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}
