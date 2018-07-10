package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.ManageItemUpgradesActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ManagementMenuActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class ChooseSetActivity extends AppCompatActivity {
    private SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_set);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        findViewById(R.id.gear_card).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "gear");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.mine_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "mine");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.targa_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "targa");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.jagono_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "jargono");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.derelict_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "derelict");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.trederra_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "trederra");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.cynder_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "cynder");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.personal).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "personal");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            onBackPressed();
        });
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    public void onBackPressed() {
        Intent intent = new Intent(this, ManagementMenuActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}
