package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems.ChooseSetActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.ExamineInventoryActivity;
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

        findViewById(R.id.btn_inventory).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ExamineInventoryActivity.class);
            intent.putExtra("location", "Inventory");
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_new_gear).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ChooseSetActivity.class);
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
        findViewById(R.id.btn_transport).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ExamineInventoryActivity.class);
            intent.putExtra("location", "Transport");
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
