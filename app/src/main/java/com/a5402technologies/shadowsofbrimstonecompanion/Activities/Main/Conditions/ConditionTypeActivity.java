package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Conditions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ShadowsOfBrimstoneActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ConditionEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class ConditionTypeActivity extends AppCompatActivity {
    SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_type);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        findViewById(R.id.injuries).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ChooseAddRemoveActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("condition_type", ConditionEnum.INJURY.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.madness).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ChooseAddRemoveActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("condition_type", ConditionEnum.MADNESS.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.mutations).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ChooseAddRemoveActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("condition_type", ConditionEnum.MUTATION.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.other_conditions).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ChooseAddRemoveActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("condition_type", ConditionEnum.OTHER.label());
            startActivity(intent);
            finish();
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}


