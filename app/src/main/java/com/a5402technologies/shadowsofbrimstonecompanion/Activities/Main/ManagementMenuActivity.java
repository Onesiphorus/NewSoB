package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems.FoundGearActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.ChangeLoadoutActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.ManageItemUpgradesActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.RemoveItems.ChooseTypeToRemoveActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Town.VisitTownActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class ManagementMenuActivity extends Activity {
    SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_management_menu);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        findViewById(R.id.btn_new_gear).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_change_loadout).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ChangeLoadoutActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_remove_gear).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ChooseTypeToRemoveActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_visit_town).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitTownActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_manage_item_upgrades).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ManageItemUpgradesActivity.class);
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
        Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }

}
