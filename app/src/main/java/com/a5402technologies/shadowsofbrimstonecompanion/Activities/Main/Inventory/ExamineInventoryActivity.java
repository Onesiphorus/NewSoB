package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ManagementMenuActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ShadowsOfBrimstoneActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.GearTypeEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class ExamineInventoryActivity extends AppCompatActivity {

    SobCharacter sobCharacter;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type_to_remove);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        location = getIntent().getStringExtra("location");

        findViewById(R.id.btn_gear).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ExamineActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.GEAR.label());
            intent.putExtra("location", location);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_clothing).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ExamineActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.CLOTHING.label());
            intent.putExtra("location", location);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_melee).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ExamineActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.HAND_WEAPONS.label());
            intent.putExtra("location", location);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_ranged).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ExamineActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.RANGED_WEAPONS.label());
            intent.putExtra("location", location);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_attachments).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ExamineActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.GEAR_UPGRADES.label());
            intent.putExtra("location", location);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_home).setOnClickListener((View view ) -> {
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
        intent.putExtra("location", location);
        startActivity(intent);
        finish();
    }
}
