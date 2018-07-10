package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ManagementMenuActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.GearTypeEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class FoundGearActivity extends AppCompatActivity {

    private SobCharacter sobCharacter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_gear);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        findViewById(R.id.btn_gear).setOnClickListener((View view) -> {
            Intent intent = new Intent(FoundGearActivity.this, AddGearBaseActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.GEAR.label());
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_clothing).setOnClickListener((View view) -> {
            Intent intent = new Intent(FoundGearActivity.this, AddGearBaseActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.CLOTHING.label());
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_melee).setOnClickListener((View view) -> {
            Intent intent = new Intent(FoundGearActivity.this, AddGearBaseActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.HAND_WEAPONS.label());
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_ranged).setOnClickListener((View view) -> {
            Intent intent = new Intent(FoundGearActivity.this, AddGearBaseActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.RANGED_WEAPONS.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_attachments).setOnClickListener((View view) -> {
            Intent intent = new Intent(FoundGearActivity.this, AddGearBaseActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", GearTypeEnum.GEAR_UPGRADES.label());
            startActivity(intent);
            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ManagementMenuActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}
