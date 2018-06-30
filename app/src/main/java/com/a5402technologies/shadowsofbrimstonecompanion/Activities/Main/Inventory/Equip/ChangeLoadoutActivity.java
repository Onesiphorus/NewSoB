package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ManagementMenuActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ClothingSlotsEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import static java.lang.Boolean.TRUE;

public class ChangeLoadoutActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_loadout);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        for (Clothing clothing : sobCharacter.getClothing()) {
            if (clothing.getEquipped().equals(TRUE)) {
                if (clothing.getHat().equals(TRUE)) {
                    btn = findViewById(R.id.btn_equip_hat);
                    btn.setText(clothing.getName());
                }
                if (clothing.getFace().equals(TRUE)) {
                    btn = findViewById(R.id.btn_equip_face);
                    btn.setText(clothing.getName());
                }
                if (clothing.getShoulders().equals(TRUE)) {
                    btn = findViewById(R.id.btn_equip_shoulders);
                    btn.setText(clothing.getName());
                }
                if (clothing.getGloves().equals(TRUE)) {
                    btn = findViewById(R.id.btn_equip_gloves);
                    btn.setText(clothing.getName());
                }
                if (clothing.getPants().equals(TRUE)) {
                    btn = findViewById(R.id.btn_equip_pants);
                    btn.setText(clothing.getName());
                }
                if (clothing.getTorso().equals(TRUE)) {
                    btn = findViewById(R.id.btn_equip_torso);
                    btn.setText(clothing.getName());
                }
                if (clothing.getBoots().equals(TRUE)) {
                    btn = findViewById(R.id.btn_equip_boots);
                    btn.setText(clothing.getName());
                }
                if (clothing.getBelt().equals(TRUE)) {
                    btn = findViewById(R.id.btn_equip_belt);
                    btn.setText(clothing.getName());
                }
                if (clothing.getCoat().equals(TRUE)) {
                    btn = findViewById(R.id.btn_equip_coat);
                    btn.setText(clothing.getName());
                }
            }
        }
        findViewById(R.id.btn_equip_boots).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_boots);
            String boots = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", ClothingSlotsEnum.BOOTS.label());
            intent.putExtra("name", boots);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_coat).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_coat);
            String coat = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", ClothingSlotsEnum.COAT.label());
            intent.putExtra("name", coat);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_face).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_face);
            String face = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", ClothingSlotsEnum.FACE.label());
            intent.putExtra("name", face);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_gloves).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_gloves);
            String gloves = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", ClothingSlotsEnum.GLOVES.label());
            intent.putExtra("name", gloves);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_hat).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_hat);
            String hat = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", ClothingSlotsEnum.HAT.label());
            intent.putExtra("name", hat);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_pants).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_pants);
            String pants = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", ClothingSlotsEnum.PANTS.label());
            intent.putExtra("name", pants);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_shoulders).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_shoulders);
            String shoulders = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", ClothingSlotsEnum.SHOULDERS.label());
            intent.putExtra("name", shoulders);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_torso).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_torso);
            String torso = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", ClothingSlotsEnum.TORSO.label());
            intent.putExtra("name", torso);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_belt).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_belt);
            String belt = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", ClothingSlotsEnum.BELT.label());
            intent.putExtra("name", belt);
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
