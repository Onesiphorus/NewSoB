package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ManagementMenuActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ShadowsOfBrimstoneActivity;
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
            intent.putExtra("card_type", "Standard Equipment");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.mine_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "Mines");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.targa_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "Ancient City of Targa");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.jagono_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "Swamps of Jargono");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.derelict_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "Derelict Ship");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.trederra_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "Trederran War Zone");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.cynder_artifact).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "Caverns of Cynder");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.personal).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("card_type", "Personal");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_home).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
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
