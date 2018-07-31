package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.RemoveItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ManagementMenuActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ShadowsOfBrimstoneActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.GearTypeEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class ChooseTypeToRemoveActivity extends AppCompatActivity {
    SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type_to_remove);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        findViewById(R.id.btn_gear).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, RemoveGearActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.GEAR.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_clothing).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, RemoveGearActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.CLOTHING.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_melee).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, RemoveGearActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.HAND_WEAPONS.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_ranged).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, RemoveGearActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.RANGED_WEAPONS.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_attachments).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, RemoveGearActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.GEAR_UPGRADES.label());
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
    public void onBackPressed() {
        Intent intent = new Intent(this, ManagementMenuActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}
