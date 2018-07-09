package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.RemoveItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ManagementMenuActivity;
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
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_clothing).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, RemoveClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_melee).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, RemoveMeleeActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_ranged).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, RemoveRangedActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_attachments).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, RemoveAttachmentActivity.class);
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
