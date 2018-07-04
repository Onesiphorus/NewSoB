package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems.AddSkillActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ModifiersEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;

public class LevelUpActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_up);
        ArrayList<String> tempMods = new ArrayList<>(0);
        Button btn = findViewById(R.id.btn_cancel);
        btn.setText("Reset");
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");


        findViewById(R.id.health_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.health_value);
            tempMods.add(ModifiersEnum.MAX_HEALTH.label());
            Integer i = 0;
            for(String string : tempMods) {
                if(string.equals(ModifiersEnum.MAX_HEALTH.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.sanity_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.sanity_value);
            tempMods.add(ModifiersEnum.MAX_SANITY.label());
            Integer i = 0;
            for(String string : tempMods) {
                if(string.equals(ModifiersEnum.MAX_SANITY.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.agility_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.agility_value);
            tempMods.add(ModifiersEnum.AGILITY.label());
            Integer i = 0;
            for(String string : tempMods) {
                if(string.equals(ModifiersEnum.AGILITY.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.cunning_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.cunning_value);
            tempMods.add(ModifiersEnum.CUNNING.label());
            Integer i = 0;
            for(String string : tempMods) {
                if(string.equals(ModifiersEnum.CUNNING.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.spirit_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.spirit_value);
            tempMods.add(ModifiersEnum.SPIRIT.label());
            Integer i = 0;
            for(String string : tempMods) {
                if(string.equals(ModifiersEnum.SPIRIT.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.strength_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.strength_value);
            tempMods.add(ModifiersEnum.STRENGTH.label());
            Integer i = 0;
            for(String string : tempMods) {
                if(string.equals(ModifiersEnum.STRENGTH.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.lore_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.lore_value);
            tempMods.add(ModifiersEnum.LORE.label());
            Integer i = 0;
            for(String string : tempMods) {
                if(string.equals(ModifiersEnum.LORE.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.luck_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.luck_value);
            tempMods.add(ModifiersEnum.LUCK.label());
            Integer i = 0;
            for(String string : tempMods) {
                if(string.equals(ModifiersEnum.LUCK.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.init_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.init_value);
            tempMods.add(ModifiersEnum.INITIATIVE.label());
            Integer i = 0;
            for(String string : tempMods) {
                if(string.equals(ModifiersEnum.INITIATIVE.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.move_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.move_value);
            tempMods.add(ModifiersEnum.MOVE.label());
            Integer i = 0;
            for(String string : tempMods) {
                if(string.equals(ModifiersEnum.MOVE.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.max_grit_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.max_grit_value);
            tempMods.add(ModifiersEnum.MAX_GRIT.label());
            Integer i = 0;
            for(String string : tempMods) {
                if(string.equals(ModifiersEnum.MAX_GRIT.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        //TODO integrate MAX_GRIT on character sheet

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
            sobCharacter.setLevel(sobCharacter.getLevel() + 1);
            for(String modifier : tempMods) {
                sobCharacter.addModifier(modifier);
            }
            Intent intent = new Intent(this, AddSkillActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, LevelUpActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }

}
