package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ManagementMenuActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ShadowsOfBrimstoneActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.TraitsEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

public class ChangeLoadoutActivity extends AppCompatActivity {
    private SobCharacter sobCharacter;
    private Button btn;
    private Integer numHat = 0;
    private Integer numFace = 0;
    private Integer numShoulders = 0;
    private Integer numGloves = 0;
    private Integer numPants = 0;
    private Integer numTorso = 0;
    private Integer numBoots = 0;
    private Integer numBelt = 0;
    private Integer numCoat = 0;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_loadout);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        setClothing(sobCharacter.getClothing());
        setViews();
        setOnClickButtons();

        findViewById(R.id.btn_manage_item_upgrades).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ManageItemUpgradesActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
    }

    public void setViews() {
        tv = findViewById(R.id.num_belt);
        tv.setText(String.format(numBelt.toString()));
        tv = findViewById(R.id.num_boots);
        tv.setText(String.format(numBoots.toString()));
        tv = findViewById(R.id.num_coat);
        tv.setText(String.format(numCoat.toString()));
        tv = findViewById(R.id.num_face);
        tv.setText(String.format(numFace.toString()));
        tv = findViewById(R.id.num_gloves);
        tv.setText(String.format(numGloves.toString()));
        tv = findViewById(R.id.num_hats);
        tv.setText(String.format(numHat.toString()));
        tv = findViewById(R.id.num_pants);
        tv.setText(String.format(numPants.toString()));
        tv = findViewById(R.id.num_shoulers);
        tv.setText(String.format(numShoulders.toString()));
        tv = findViewById(R.id.num_torso);
        tv.setText(String.format(numTorso.toString()));
    }

    public void setOnClickButtons() {
        findViewById(R.id.btn_equip_boots).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_boots);
            String boots = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", TraitsEnum.BOOTS.label());
            intent.putExtra("name", boots);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_coat).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_coat);
            String coat = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", TraitsEnum.COAT.label());
            intent.putExtra("name", coat);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_face).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_face);
            String face = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", TraitsEnum.FACE.label());
            intent.putExtra("name", face);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_gloves).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_gloves);
            String gloves = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", TraitsEnum.GLOVES.label());
            intent.putExtra("name", gloves);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_hat).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_hat);
            String hat = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", TraitsEnum.HAT.label());
            intent.putExtra("name", hat);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_pants).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_pants);
            String pants = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", TraitsEnum.PANTS.label());
            intent.putExtra("name", pants);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_shoulders).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_shoulders);
            String shoulders = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", TraitsEnum.SHOULDERS.label());
            intent.putExtra("name", shoulders);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_torso).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_torso);
            String torso = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", TraitsEnum.TORSO.label());
            intent.putExtra("name", torso);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_equip_belt).setOnClickListener((View view) -> {
            btn = findViewById(R.id.btn_equip_belt);
            String belt = btn.getText().toString();
            Intent intent = new Intent(this, ChangeClothingActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("type", TraitsEnum.BELT.label());
            intent.putExtra("name", belt);
            startActivity(intent);
            finish();
        });
    }


    public void setClothing(ArrayList<Clothing> clothingList) {
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
            } else {
                if (clothing.getHat().equals(TRUE)) {
                    numHat++;
                }
                if (clothing.getFace().equals(TRUE)) {
                    numFace++;
                }
                if (clothing.getShoulders().equals(TRUE)) {
                    numShoulders++;
                }
                if (clothing.getGloves().equals(TRUE)) {
                    numGloves++;
                }
                if (clothing.getPants().equals(TRUE)) {
                    numPants++;
                }
                if (clothing.getTorso().equals(TRUE)) {
                    numTorso++;
                }
                if (clothing.getBoots().equals(TRUE)) {
                    numBoots++;
                }
                if (clothing.getBelt().equals(TRUE)) {
                    numBelt++;
                }
                if (clothing.getCoat().equals(TRUE)) {
                    numCoat++;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}
